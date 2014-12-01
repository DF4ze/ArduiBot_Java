package controleurs;

import java.util.Observable;
import java.util.Observer;

import modeles.DroneActions;
import modeles.dao.communication.beansactions.DirectionAction;
import modeles.dao.communication.beansactions.IAction;
import modeles.dao.communication.beansactions.TourelleAction;
import controleurs.socketclient.com.Emission;

public class ControleurEmission implements Observer{

	public ControleurEmission(  ) {
		
		DroneActions.getInstance().addObserver(this);
	}

	@Override
	public void update(Observable model, Object messageObj) {
		String message = (String)messageObj;
		
		if( model instanceof DroneActions ){
			if( message.equals("DIRECTION") ){
				int prio = IAction.prioMedium;
				if( DroneActions.getDirY() == 0 )
					prio = IAction.prioHighest;
				
				DirectionAction da = new DirectionAction(DroneActions.getDirY(), DroneActions.getDirX(), prio);
				Emission.addAction(da);
			}
			else if( message.equals("TOURELLE") ){
				int prio = IAction.prioMedium;
				if( DroneActions.getTourX() == DroneActions.tourXCenter && DroneActions.getTourY() == DroneActions.tourYCenter )
					prio = IAction.prioHight;

				TourelleAction ta = new TourelleAction( (int)( DroneActions.getTourX()),(int)( DroneActions.getTourY()), prio);
				Emission.addAction(ta);
			}
		}
		
	}

}
