package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import modeles.CamCat;
import modeles.CtrlCat;
import vues.CamFrame;
import exceptions.CamException;



public class Controleur implements ActionListener{

	private CtrlCat oModel;
	private CamCat oModCam;
	private CamFrame cfFrame;
	
	
	public Controleur() {
		oModel = new CtrlCat();
		
		try {
			oModCam = new CamCat();
		} catch (FileNotFoundException e) {
			if( Debug.isEnable() ){
				System.out.println("fichier de config cam non trouvé");
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			if( Debug.isEnable() ){
				System.out.println("Une adresse de cam n'est pas valide");
				e.printStackTrace();
			}
		}
		
		cfFrame = new CamFrame("DroneCtrl", oModel, oModCam);
		cfFrame.setListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		
		if( Debug.isEnable() )
			System.out.println("Action recu : "+action);
		
		if (action.equals("BTNCONNECT")) {

				 
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
			 
			 
		 }else if (action.equals("SELECTEDDEVICE")) {
			if( Debug.isEnable() )
				System.out.println("SelectedCam : "+cfFrame.getSelectedCam());
			
			oModCam.setSelectedCam(cfFrame.getSelectedCam());	
		}
		
	}

}
