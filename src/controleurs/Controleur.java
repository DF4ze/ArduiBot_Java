package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			
		}else if (action.equals("BTNSAVECAMS")) {			
			oModCam.saveCams();	
			
			
		}else if( action.equals("BTNADDCAM") ){
			cfAddFrame = new AddCamFrame("Ajouter une caméra");
			cfAddFrame.setListener(this);
			
		}else if( action.equals("OKADDCAM") ){
			// Do Job
			cfAddFrame.dispose();
		}else if( action.equals("ANNULERADDCAM") ){
			cfAddFrame.dispose();
		}
		
	}

}
