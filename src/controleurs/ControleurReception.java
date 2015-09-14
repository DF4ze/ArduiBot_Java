package controleurs;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import modeles.catalogues.CtrlCat;
import modeles.dao.communication.beansinfos.SensorInfo;
import modeles.dao.communication.beansinfos.SensorInfoDist;
import modeles.dao.communication.beansinfos.ShellInfo;
import modeles.dao.communication.beansinfos.StateInfo;
import modeles.dao.communication.beansinfos.TextInfo;

public class ControleurReception implements Observer {
	
	private CtrlCat oModel;

	public ControleurReception( CtrlCat oModel ) {
		this.oModel = oModel;
	}

	@Override
	public void update(Observable arg0, Object object) {
		// TODO Auto-generated method stub

		if( object instanceof SensorInfo ){
			SensorInfo si = (SensorInfo)object;
			if( si.getSensor() == 0 ){	
				HashMap<Integer, Integer> distances = new HashMap<Integer, Integer>();
				distances.put(((SensorInfoDist)si).getPos(), si.getValue());
				oModel.setDistances(distances);
				
				System.out.println("Capteur :" +si.getSensor() + " pos : "+((SensorInfoDist)si).getPos()+ " value : "+si.getValue());
			}else
				System.out.println("Capteur :" +si.getSensor() + " value : "+si.getValue());
			
		}else if( object instanceof StateInfo ){
			StateInfo si = (StateInfo)object;
			System.out.println("State :" +si.getMateriel() + " statut : "+si.getStat());
			
			if( si.getMateriel() == 0 ){
				oModel.setStandByCheck(si.getStat());
			}
			
		}else if( object instanceof ShellInfo ){
			ShellInfo si = (ShellInfo)object;
			String cmd = "";
			for( String txt : si.getCommand() )
				cmd += txt+" ";
			
			System.out.println("Shell info : "+si.getName()+"\ncmd \"" +cmd + "\" \nresultat : "+si.getResult());
			
		}else if( object instanceof TextInfo ){
			TextInfo ti = (TextInfo)object;
			System.out.println("Info :" +ti.getInfo());
			
		}else
			System.err.println("IINFO ok mais non reconnu apres");
	}

}
