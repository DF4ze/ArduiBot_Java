package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import modeles.CamCat;
import modeles.CtrlCat;
import vues.AddCamFrame;
import vues.CamFrame;
import exceptions.CamException;



public class Controleur implements ActionListener{

	private CtrlCat oModel;
	private CamCat oModCam;
	private CamFrame cfFrame;
	private AddCamFrame cfAddFrame;
	
	
	public Controleur() {
		oModel = new CtrlCat();
		oModCam = new CamCat();
		
		cfFrame = new CamFrame("DroneCtrl", oModel, oModCam);
		cfFrame.setListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		
		if( Debug.isEnable() )
			System.out.println("Action recu : "+action);
		
		if (action.equals("BTNCONNECTCAM")) {

				 
			try {
				cfFrame.showCam();
				oModel.setDirectionEnable(true);
				oModel.setTourelleEnable(true);
				oModel.setExtraEnable(true);
				oModel.setCameraEnable(true);
				 
				
				
			} catch (CamException e1) {
				
				cfFrame.setCamError(e1.getMessage());

				oModel.setDirectionEnable(false);
				oModel.setTourelleEnable(false);
				oModel.setExtraEnable(false);
				oModel.setCameraEnable(false);

				if( Debug.isEnable() )
					e1.printStackTrace();
			}
			 
			 
		 }else if (action.equals("BTNSTOPCAM")) {
				cfFrame.stopCam();	
				
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
		}
		
	}

}
