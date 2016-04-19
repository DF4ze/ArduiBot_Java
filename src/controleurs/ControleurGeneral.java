package controleurs;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.simple.parser.ParseException;

import controleurs.audio.MicroStream;
import controleurs.inputs.JoystickControllerPoller;
import controleurs.socketclient.com.Emission;
import controleurs.socketclient.com.SocketClient;
import exceptions.CamException;
import modeles.DroneActions;
import modeles.catalogues.CamCat;
import modeles.catalogues.CtrlCat;
import modeles.catalogues.PilotCat;
import modeles.catalogues.SocketCat;
import modeles.dao.communication.beansactions.SpeakAction;
import modeles.dao.communication.beansactions.VolumeAction;
import modeles.inputs.JoystickCat;
import modeles.inputs.KeyCat;
import vues.AddCamFrame;
import vues.AddSocketFrame;
import vues.CamFrame;




public class ControleurGeneral implements ActionListener{

	private CtrlCat 		oModCtrl;
	private CamCat 			oModCam;
	private PilotCat 		oModPilot;
	private KeyCat 			oModKey;
	private JoystickCat 	oModJoy;
	private SocketCat 		oModSock;
	
	private CamFrame 			cfFrame;
	private AddCamFrame 		cfAddFrameCam;
	private AddSocketFrame 		cfAddFrameSock;
	
	private ControleurPilotage 	cpCtrlPil;
	private ControleurReception cr;
//	private ControleurVoix 		cv;
	
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
		oModSock = new SocketCat();
		
		// la vue
		cfFrame = new CamFrame( "DroneCtrl", oModCtrl, oModCam, oModPilot, oModSock );
		
		// Autres controleurs
		cpCtrlPil = new ControleurPilotage( cfFrame, oModCtrl, oModPilot, oModKey );
		/*cpCtrlEmiss = */new ControleurEmission( );
		cr = new ControleurReception(oModCtrl);
		/*cv = */new ControleurVoix(oModCtrl, oModSock);
		
		// Attribution des listeners
		cfFrame.setListener(this);
		cfFrame.setPilotListener( cpCtrlPil );
		cfFrame.setKeyListener( cpCtrlPil );
		DroneActions.setObserv(oModPilot);
		DroneActions.setObserv(oModCtrl);
		
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
				System.out.println( "Joystick not found" );

		// On prépare le socket
		socket = new SocketClient(oModSock, cr, oModSock);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		
		if( Debug.isEnable() )
			System.out.println("Controleur General, Action recu : "+action);
		
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
			cfAddFrameCam = new AddCamFrame("Ajouter une caméra", oModCam);
			cfAddFrameCam.setListener(this);
			
		}else if( action.equals("BTNDELCAM") ){
			oModCam.delCam( cfFrame.getSelectedCam() );
			
		}else if( action.equals("OKADDCAM") ){
			try {
				oModCam.addCam( cfAddFrameCam.getValues() );
				cfAddFrameCam.dispose();
				
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
			cfAddFrameCam.dispose();
			
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
			
		}else if( action.equals("CBSTANDBY") ){
			oModCtrl.setStandByCheck(!oModCtrl.isStandByCheck());
			
			//oModCtrl.setDirectionEnable(!oModCtrl.isStandByCheck());
			//oModCtrl.setTourelleEnable(!oModCtrl.isStandByCheck()); 
			
		}else if( action.equals("BTNWEBCAMSERVICE") ){
			oModCtrl.setWebCamService(!oModCtrl.isWebCamService());
			
		}else if( action.equals("REDUCECTRL") ){
			oModCtrl.setReduceCtrl(!oModCtrl.isReduceCtrl());
			
			
		}else if( action.equals("REDUCEOPTS") ){
			oModCtrl.setReduceOpts(!oModCtrl.isReduceOpts());
			
			
		}else if( action.equals("CBLOCALSOUND") ){
			oModCtrl.setSonLocalCheck(!oModCtrl.isSonLocalCheck());
			
			
		}else if( action.equals("CBDISTANTSOUND") ){
			oModCtrl.setSonDistantCheck( !oModCtrl.isSonDistantCheck() );
			
			
		}else if( action.equals("CBACTIVRECO") ){
			oModCtrl.setRecoVocCheck( !oModCtrl.isRecoVocCheck() );
			
			
		}else if( action.equals("RECOVOCENABLE") ){
			oModCtrl.setRecoVocEnable( !oModCtrl.isRecoVocEnable());
			
//			buttonSay.setActionCommand( "BUTTONSAY" );
//			rbTTSEspeak.addActionListener(ac);
//			rbTTSEspeak.setActionCommand( "RBESPEAK" );
//			rbTTSPico.addActionListener(ac);
//			rbTTSPico.setActionCommand( "RBPICO" );
//			textField.addActionListener(ac);
//			textField.setActionCommand( "TEXTTOSAY" );
		}else if( action.equals("BUTTONSAY") ){
			SpeakAction sa = new SpeakAction();
			oModCtrl.setTextToSay( cfFrame.getTextToSay() );
			sa.setText(oModCtrl.getTextToSay());
			if( oModCtrl.getTtsSelected() == CtrlCat.ttsPico )
				sa.setTts(SpeakAction.ttsPico);
			else if( oModCtrl.getTtsSelected() == CtrlCat.ttsEspeak )
				sa.setTts(SpeakAction.ttsEspeak);
			
			if( Debug.isEnable() )
				System.out.println("Texte a lire : "+oModCtrl.getTextToSay());
			Emission.addAction(sa);

		}else if( action.equals("RBESPEAK") ){
			oModCtrl.setTtsSelected(CtrlCat.ttsEspeak);

		}else if( action.equals("RBPICO") ){
			oModCtrl.setTtsSelected(CtrlCat.ttsPico);
			
			
			
			
		}else if( action.equals("DISTANTSOUNDPLUS") ){
			VolumeAction va = new VolumeAction(true, VolumeAction.volumePourCentDefault);
			Emission.addAction(va);

		}else if( action.equals("DISTANTSOUNDMINUS") ){
			VolumeAction va = new VolumeAction(false, VolumeAction.volumePourCentDefault);
			Emission.addAction(va);

		}else if( action.equals("DISTANTSOUNDMUTE") ){
			oModCtrl.setDistantSoundMute(!oModCtrl.isDistantSoundMute());
			VolumeAction va = new VolumeAction(!oModCtrl.isDistantSoundMute(), true);
			
			Emission.addAction(va);
			
			
			
			
		}else if( action.equals("CBSTREAMMIC") ){
			oModCtrl.setStreamedMic(!oModCtrl.isStreamedMic());
			if( oModCtrl.isStreamedMic() )
				MicroStream.start(oModSock);
			else
				MicroStream.stop();
			
			
			
		}else if( action.equals("BTNADDSOCKET") ){
			cfAddFrameSock = new AddSocketFrame("Ajouter un Socket", oModSock);
			cfAddFrameSock.setListener(this);
			
		}else if( action.equals("OKADDSOCKET") ){
			oModSock.addSocket( cfAddFrameSock.getValues() );
			cfAddFrameSock.dispose();
			
		}else if( action.equals("ANNULERADDSOCKET") ){
			oModSock.addSocket( cfAddFrameSock.getValues() );
			cfAddFrameSock.dispose();
			
		}else if( action.equals("BTNDELSOCKET") ){
			oModSock.delSocket( cfFrame.getSelectedSocket() );
			
		}else if( action.equals("BTNCONNECTSOCKET") ){
			if( socket.start() ){
				//oModCtrl.setDirectionEnable(true);
				//oModCtrl.setTourelleEnable(true);
				oModCtrl.setExtraEnable(true);
				oModCtrl.setRecoVocEnable(true);
				oModCtrl.setTtsEnable(true);
				oModCtrl.setDistantSoundEnable(true);
				oModCtrl.setStreamMicEnable(true);
				
				oModSock.setConnected(true);
			}else{
				oModSock.setConnected(false);
				oModCtrl.setRecoVocEnable(false);
				oModCtrl.setTtsEnable(false);
				oModCtrl.setDistantSoundEnable(false);
				oModCtrl.setStreamMicEnable(false);
			}
			
		}else if( action.equals("BTNSTOPSOCKET") ){
			socket.stop();
			oModCtrl.setExtraEnable(false);
			oModCtrl.setDirectionEnable(false);
			oModCtrl.setTourelleEnable(false);
			oModCtrl.setRecoVocEnable(false);
			oModCtrl.setTtsEnable(false);
			oModCtrl.setDistantSoundEnable(false);
			oModCtrl.setStreamMicEnable(false);
			
			
		}else if (action.equals("BTNSAVESOCKETS")) {			
			try {
				oModSock.writeSockets();
			} catch (IOException | ParseException e1) {
				JOptionPane.showMessageDialog(null,
					    "Erreur lors de la sauvegarde des caméras...",
					    "Erreur de sauvegarde des Caméras",
					    JOptionPane.ERROR_MESSAGE);
			}
			
			
		}else if (action.equals("BTNREADSOCKETS")) {			
			try {
				oModSock.readSockets();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,
					    "Erreur lors de la lecture du fichier de caméra...",
					    "Erreur de chargement des Caméras",
					    JOptionPane.ERROR_MESSAGE);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null,
					    "Erreur lors du décryptage du fichier de caméra...",
					    "Erreur de chargement des Caméras",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		}else if (action.equals("SELECTEDSOCKET")) {			
			oModSock.setSelected( cfFrame.getSelectedSocket() );
		
		}
	}
}
