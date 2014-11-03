package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;

import modeles.CamCat;
import modeles.CtrlCat;
import modeles.GraphCat;

import org.json.simple.parser.ParseException;

import vues.AddCamFrame;
import vues.CamFrame;
import exceptions.CamException;



public class ControleurGeneral implements ActionListener{

	private CtrlCat oModCtrl;
	private CamCat oModCam;
	@SuppressWarnings("unused")
	private GraphCat oModGraph;
	private CamFrame cfFrame;
	private AddCamFrame cfAddFrame;
	private ControleurPilotage cpCtrlPil;
	
	
	public ControleurGeneral() {
		// les modeles
		oModCtrl = new CtrlCat();
		oModCam = new CamCat();
		oModGraph = new GraphCat();
		
		// la vue
		cfFrame = new CamFrame("DroneCtrl", oModCtrl, oModCam);
		
		// Autres controleurs
		cpCtrlPil = new ControleurPilotage( cfFrame, oModCtrl );
		
		// Attribution des listeners
		cfFrame.setListener(this);
		cfFrame.setPilotListener(cpCtrlPil);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		
		if( Debug.isEnable() )
			System.out.println("Action recu : "+action);
		
		if (action.equals("BTNCONNECTCAM")) {
			try {
				cfFrame.showCam();
				oModCtrl.setCameraEnable(true);
				 
			} catch (CamException e1) {
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
					    "Erreur lors de la sauvegarde des cam�ras...",
					    "Erreur de sauvegarde des Cam�ras",
					    JOptionPane.ERROR_MESSAGE);
			}
			
			
		}else if (action.equals("BTNREADCAMS")) {			
			try {
				oModCam.readCams();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,
					    "Erreur lors de la lecture du fichier de cam�ra...",
					    "Erreur de chargement des Cam�ras",
					    JOptionPane.ERROR_MESSAGE);
			} catch (ParseException|CamException e1) {
				JOptionPane.showMessageDialog(null,
					    "Erreur lors du d�cryptage du fichier de cam�ra...",
					    "Erreur de chargement des Cam�ras",
					    JOptionPane.ERROR_MESSAGE);
			}
			
			
		}else if( action.equals("BTNADDCAM") ){
			cfAddFrame = new AddCamFrame("Ajouter une cam�ra", oModCam);
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
					    "Erreur de cr�ation de Cam�ra",
					    JOptionPane.ERROR_MESSAGE);
				
			} catch (CamException e1) {
				JOptionPane.showMessageDialog(null,
					    e1.getMessage(),
					    "Erreur de cr�ation de Cam�ra",
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
					    "Erreur lors de l'�criture de l'image",
					    "Prendre une photo",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		}else if( action.equals("BTNTAKEVIDEO") ){
			oModCam.takeVideo();
			
		} 
	}
}
