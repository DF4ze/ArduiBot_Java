package controleurs.socketclient.com;
import java.io.IOException;
import java.io.ObjectInputStream;

import modeles.dao.communication.beansinfos.IInfo;
import modeles.dao.communication.beansinfos.SensorInfo;
import modeles.dao.communication.beansinfos.ShellInfo;
import modeles.dao.communication.beansinfos.StateInfo;
import modeles.dao.communication.beansinfos.TextInfo;
import controleurs.Debug;


public class Reception implements Runnable {

//	private BufferedReader in;
	private ObjectInputStream inObject;
//	private String message = null;
//	private IInfo info = null;
	private boolean bRunning = true;
	
	public Reception(ObjectInputStream in){
		
		this.inObject = in;
	}
	
	public void stop(){
		bRunning = false;
		inObject = null;
	}	
	
	public void run() {
		
		while( bRunning ){
	        try {
	        	
				Object object = inObject.readObject();
				
				if( object instanceof IInfo ){
					if( object instanceof SensorInfo ){
						SensorInfo si = (SensorInfo)object;
						System.out.println("Capteur :" +si.getSensor() + " value : "+si.getValue());
						
					}else if( object instanceof StateInfo ){
						StateInfo si = (StateInfo)object;
						System.out.println("State :" +si.getMateriel() + " statut : "+si.getStat());
						
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
						throw new ClassNotFoundException("IINFO ok mais non reconnu apres");
				}else
					throw new ClassNotFoundException("Pas IINFO");
				
		    } catch (IOException e) {
				System.out.println("Le serveur s'est deconnecté");
				break;
				//e.printStackTrace();
			} catch( NullPointerException e ){
				
			} catch (ClassNotFoundException e) {
				if( Debug.isEnable() )
					System.err.println("Reception Socket : Type de classe non reconnue : "+e.getMessage());
			}
		}
		if( Debug.isEnable() )
			System.out.println("Sortie propre du Thread Reception");
	}

}