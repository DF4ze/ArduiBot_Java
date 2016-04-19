package controleurs;

import java.util.Observable;
import java.util.Observer;

import controleurs.socketclient.com.Emission;
import modeles.DroneActions;
import modeles.dao.communication.beansactions.DirectionAction;
import modeles.dao.communication.beansactions.ExtraAction;
import modeles.dao.communication.beansactions.IAction;
import modeles.dao.communication.beansactions.TourelleAction;

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
			else if( message.equals("LIGHTCHECK") ){
				ExtraAction ae = new ExtraAction(IAction.typeLight, IAction.Light, DroneActions.isLight()?1:0, IAction.prioMedium);
				Emission.addAction(ae);
			}
			else if( message.equals("LAZERCHECK") ){
				ExtraAction ae = new ExtraAction(IAction.typeLight, IAction.Lazer, DroneActions.isLazer()?1:0, IAction.prioMedium);
				Emission.addAction(ae);				
			}			
			else if( message.equals("STROBCHECK") ){
				ExtraAction ae = new ExtraAction(IAction.typeLight, IAction.Strobe, DroneActions.isStrob()?1:0, IAction.prioMedium);
				Emission.addAction(ae);				
			}
			else if( message.equals("STANDBYCHECK") ){
				ExtraAction ae = new ExtraAction(IAction.typeAlim, IAction.alimStandBy, DroneActions.isStandBy()?1:0, IAction.prioMedium);
				Emission.addAction(ae);
			}
			else if( message.equals("WEBCAMSERVICE") ){
				ExtraAction ae = new ExtraAction(IAction.typeWebcam, IAction.webcamTour, DroneActions.isWebCamService()?1:0, IAction.prioMedium);
				Emission.addAction(ae);
			}
		}
		
	}

}
