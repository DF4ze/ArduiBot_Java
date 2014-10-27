package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modeles.CtrlCat;
import vues.CamFrame;
import exceptions.CamException;



public class Controleur implements ActionListener{

	private CtrlCat oModel;
	private CamFrame cfFrame;
	
	
	public Controleur() {
		oModel = new CtrlCat();
		cfFrame = new CamFrame("DroneCtrl", oModel);
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
				System.out.println("SelectedCam : "+cfFrame.getSelectedDevice());
			
			oModel.setSelectedDevice(cfFrame.getSelectedDevice());	
		}
		
	}

}
