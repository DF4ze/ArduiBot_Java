package controleurs;

import java.util.Observable;
import java.util.Observer;

import controleurs.socketclient.com.Emission;
import modeles.dao.communication.beanssend.DirectionAction;
import modeles.dao.communication.beanssend.IAction;
import modeles.graphical.PilotCat;

public class ControleurEmission implements Observer{

	private PilotCat oModPilot;
	
	public ControleurEmission( PilotCat oMod ) {
		oModPilot = oMod;
		oModPilot.addObserver(this);
	}

	@Override
	public void update(Observable model, Object messageObj) {
		String message = (String)messageObj;
		
		if( model instanceof PilotCat ){
			if( message.equals("BGDIRECTION") ){
				if( oModPilot.isDirMove() ){
					DirectionAction da = new DirectionAction(oModPilot.getMaxVertSliderDirPos(), oModPilot.getMaxHoriSliderDirPos());
					Emission.addAction(da);
				}else{
					DirectionAction da = new DirectionAction(0, 0, IAction.prioHighest);
					Emission.addAction(da);
				}
			}
		}
		
	}

}
