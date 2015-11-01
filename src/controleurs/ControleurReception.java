package controleurs;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import modeles.catalogues.CtrlCat;
import modeles.dao.communication.beansactions.PongAction;
import modeles.dao.communication.beansinfos.IInfo;
import modeles.dao.communication.beansinfos.PingInfo;
import modeles.dao.communication.beansinfos.SensorInfo;
import modeles.dao.communication.beansinfos.SensorInfoDist;
import modeles.dao.communication.beansinfos.ShellInfo;
import modeles.dao.communication.beansinfos.StateInfo;
import modeles.dao.communication.beansinfos.TextInfo;
import controleurs.socketclient.com.Emission;

public class ControleurReception implements Observer {
	
	private CtrlCat oModel;

	public ControleurReception( CtrlCat oModel ) {
		this.oModel = oModel;
	}

	@Override
	public void update(Observable arg0, Object object) {

		if( object instanceof SensorInfo ){
			SensorInfo si = (SensorInfo)object;
			if( si.getSensor() == IInfo.sensorDistance ){	
				HashMap<Integer, Float> distances = new HashMap<Integer, Float>();
				distances.put(((SensorInfoDist)si).getPos(), si.getValue());
				oModel.setDistances(distances);
				
				if( Debug.isEnable() )
					System.out.println("Capteur :" +si.getSensor() + " pos : "+((SensorInfoDist)si).getPos()+ " value : "+si.getValue());
				
			}else if( si.getSensor() == IInfo.sensorVolt ){
				oModel.setVoltage(si.getValue());
				
				if( Debug.isEnable() )
					System.out.println("Capteur :" +si.getSensor() + " value : "+si.getValue());
			}else
				if( Debug.isEnable() )
					System.out.println("Capteur :" +si.getSensor() + " value : "+si.getValue());
			
		}else if( object instanceof StateInfo ){
			StateInfo si = (StateInfo)object;
			
			if( Debug.isEnable() )
				System.out.println("State :" +si.getMateriel() + " statut : "+si.getStat());
			
			if( si.getMateriel() == 0 ){
				oModel.setStandByCheck(si.getStat());
			}
			
		}else if( object instanceof ShellInfo ){
			ShellInfo si = (ShellInfo)object;
			String cmd = "";
			for( String txt : si.getCommand() )
				cmd += txt+" ";
			
			if( Debug.isEnable() )
				System.out.println("Shell info : "+si.getName()+"\ncmd \"" +cmd + "\" \nresultat : "+si.getResult());
			
		}else if( object instanceof TextInfo ){
			TextInfo ti = (TextInfo)object;
			if( Debug.isEnable() )
				System.out.println("Info :" +ti.getInfo());
			
		}else if( object instanceof PingInfo ){
			PingInfo pi = (PingInfo)object;
			Emission.addAction(new PongAction(pi.getTimeStamp()));
			if( Debug.isEnable() )
				System.out.println("Reception Ping" );
			
		}else
			if( Debug.isEnable() )
				System.err.println("IINFO ok mais non reconnu apres");
	}

}
