package controleurs;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modeles.DroneActions;
import modeles.catalogues.CamCat;
import modeles.catalogues.CtrlCat;
import modeles.catalogues.PilotCat;
import modeles.dao.communication.ArduiBotServer;
import modeles.inputs.JoystickCat;
import modeles.inputs.KeyCat;

import org.json.simple.parser.ParseException;

import vues.AddCamFrame;
import vues.CamFrame;
import controleurs.inputs.JoystickControllerPoller;
import controleurs.socketclient.SocketClient;
import exceptions.CamException;



public class ControleurGeneral implements ActionListener{

	private CtrlCat 		oModCtrl;
	private CamCat 			oModCam;
	private PilotCat 		oModPilot;
	private KeyCat 			oModKey;
	private JoystickCat 	oModJoy;
	private ArduiBotServer 	oModSock;
	
	private CamFrame 			cfFrame;
	private AddCamFrame 		cfAddFrame;
	private ControleurPilotage 	cpCtrlPil;
//	private ControleurEmission 	cpCtrlEmiss;
	
	private SocketClient 		socket;
	
	public ControleurGeneral() {
		
        try {
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
//        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            if( Debug.isEnable() )
            	e.printStackTrace();
        }		
		
		// les modeles
		oModPilot = new PilotCat();
		oModCtrl = new CtrlCat( oModPilot );
		oModCam = new CamCat();
		oModKey = new KeyCat();
		oModJoy = new JoystickCat();
		oModSock = new ArduiBotServer();
		
		// la vue
		cfFrame = new CamFrame( "DroneCtrl", oModCtrl, oModCam, oModPilot );
		
		// Autres controleurs
		cpCtrlPil = new ControleurPilotage( cfFrame, oModCtrl, oModPilot, oModKey );
		/*cpCtrlEmiss = */new ControleurEmission( );
		
		// Attribution des listeners
		cfFrame.setListener(this);
		cfFrame.setPilotListener( cpCtrlPil );
		cfFrame.setKeyListener( cpCtrlPil );
		DroneActions.setObserv(oModPilot);
		
		if( oModJoy.isControllerFound() ){
			// On ajoute le controleur de pilotage en tant qu'observeur
			oModJoy.addObserver(cpCtrlPil);
			
			// Création du Poller
			JoystickControllerPoller cp = new JoystickControllerPoller( oModJoy );
			cp.setDaemon(true);

			// Lancement du poll
			cp.start();
		}else
			if( Debug.isEnable() )
				System.out.println( "Controller not Found" );

		// On prépare la socket
		socket = new SocketClient(oModSock);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		
		if( Debug.isEnable() )
			System.out.println("Action recu : "+action);
		
		if (action.equals("BTNCONNECTCAM")) {
			try {
				cfFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				cfFrame.showCam();
				oModCtrl.setCameraEnable(true);
				cfFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				socket.start();
				
			} catch (CamException e1) {
				cfFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				cfFrame.setCamError(e1.getMessage());
				oModCtrl.setCameraEnable(false);
				socket.stop();
				
				if( Debug.isEnable() )
					e1.printStackTrace();
			}
			  
		 }else if (action.equals("BTNSTOPCAM")) {
				cfFrame.stopCam();	
				socket.stop();
				
				oModCtrl.setCameraEnable(false);
				
		}else if (action.equals("SELECTEDDEVICE")) {
				if( Debug.isEnable() )
					System.out.println("SelectedCam : "+cfFrame.getSelectedCam());
				
				oModCam.setSelectedCam(cfFrame.getSelectedCam());	
				
		}else if (action.equals("BTNSAVECAMS")) {			
			try {
				oModCam.saveCams();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,
					    "Erreur lors de la sauvegarde des caméras...",
					    "Erreur de sauvegarde des Caméras",
					    JOptionPane.ERROR_MESSAGE);
			}
			
			
		}else if (action.equals("BTNREADCAMS")) {			
			try {
				oModCam.readCams();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,
					    "Erreur lors de la lecture du fichier de caméra...",
					    "Erreur de chargement des Caméras",
					    JOptionPane.ERROR_MESSAGE);
			} catch (ParseException|CamException e1) {
				JOptionPane.showMessageDialog(null,
					    "Erreur lors du décryptage du fichier de caméra...",
					    "Erreur de chargement des Caméras",
					    JOptionPane.ERROR_MESSAGE);
			}
			
			
		}else if( action.equals("BTNADDCAM") ){
			cfAddFrame = new AddCamFrame("Ajouter une caméra", oModCam);
			cfAddFrame.setListener(this);
			
		}else if( action.equals("BTNDELCAM") ){
			oModCam.delCam( cfFrame.getSelectedCam() );
			
		}else if( action.equals("OKADDCAM") ){
			try {
				oModCam.addCam( cfAddFrame.getValues() );
				cfAddFrame.dispose();
				
			} catch (MalformedURLException e1) {
				JOptionPane.showMessageDialog(null,
					    "L'URL n'est pas correcte.",
					    "Erreur de création de Caméra",
					    JOptionPane.ERROR_MESSAGE);
				
			} catch (CamException e1) {
				JOptionPane.showMessageDialog(null,
					    e1.getMessage(),
					    "Erreur de création de Caméra",
					    JOptionPane.ERROR_MESSAGE);
			}
			
			
		}else if( action.equals("ANNULERADDCAM") ){
			cfAddFrame.dispose();
			
		}else if( action.equals("BTNTAKEPICTURE") ){
			try {
				oModCam.takePicture();
			} catch (IOException e1) {
				if( Debug.isEnable() )
					e1.printStackTrace();
				
				JOptionPane.showMessageDialog(null,
					    "Erreur lors de l'écriture de l'image",
					    "Prendre une photo",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		}else if( action.equals("BTNTAKEVIDEO") ){
			oModCam.takeVideo();
			
		}else if( action.equals("CBLIGHT") ){
			oModCtrl.setLightCheck(!oModCtrl.isLightCheck());
			
		}else if( action.equals("CBSTROB") ){
			oModCtrl.setStrobCheck(!oModCtrl.isStrobCheck());
			
		}else if( action.equals("CBLAZER") ){
			oModCtrl.setLazerCheck(!oModCtrl.isLazerCheck());
			
		}else if( action.equals("REDUCECTRL") ){
			oModCtrl.setReduceCtrl(!oModCtrl.isReduceCtrl());;
		} 
	}
}
