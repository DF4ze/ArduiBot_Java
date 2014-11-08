package controleurs;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modeles.CamCat;
import modeles.CtrlCat;
import modeles.GraphPilotCat;
import modeles.KeyCat;

import org.json.simple.parser.ParseException;

import vues.AddCamFrame;
import vues.CamFrame;
import exceptions.CamException;



public class ControleurGeneral implements ActionListener{

	private CtrlCat oModCtrl;
	private CamCat oModCam;
	private GraphPilotCat oModGraph;
	private KeyCat oModKey;
	
	private CamFrame cfFrame;
	private AddCamFrame cfAddFrame;
	private ControleurPilotage cpCtrlPil;
	
	
	public ControleurGeneral() {
		
        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
		
		
		// les modeles
		oModGraph = new GraphPilotCat();
		oModCtrl = new CtrlCat( oModGraph );
		oModCam = new CamCat();
		oModKey = new KeyCat();
		
		// la vue
		cfFrame = new CamFrame("DroneCtrl", oModCtrl, oModCam, oModGraph);
		
		// Autres controleurs
		cpCtrlPil = new ControleurPilotage( cfFrame, oModCtrl, oModGraph, oModKey );
		
		// Attribution des listeners
		cfFrame.setListener(this);
		cfFrame.setPilotListener(cpCtrlPil);
		cfFrame.setKeyListener(cpCtrlPil);

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
				 
			} catch (CamException e1) {
				cfFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				cfFrame.setCamError(e1.getMessage());
				oModCtrl.setCameraEnable(false);

				if( Debug.isEnable() )
					e1.printStackTrace();
			}
			  
		 }else if (action.equals("BTNSTOPCAM")) {
				cfFrame.stopCam();	
				
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
			
		} 
	}
}
