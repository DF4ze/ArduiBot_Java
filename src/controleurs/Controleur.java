package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modeles.CtrlCat;
import vues.CamFrame;



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
			 if( oModel.isDirectionEnable() )
				 oModel.setDirectionEnable(false);
			 else{
				 oModel.setDirectionEnable(true);
				 cfFrame.showCam();
				 cfFrame.repaint();
			 }
			 
		 }else 
		
		if (action.equals("COMBCAMCHOIX")) {
			if( Debug.isEnable() )
				System.out.println("SelectedCam : "+cfFrame.setSelectedDevice());
			
			oModel.setSelectedDevice(cfFrame.setSelectedDevice());	
		}
		
	}

}
