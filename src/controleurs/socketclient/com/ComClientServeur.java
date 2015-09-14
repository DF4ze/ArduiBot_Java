package controleurs.socketclient.com;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;

import controleurs.ControleurReception;
import controleurs.Debug;


public class ComClientServeur implements Runnable {

	private Socket socket;
	private ObjectOutputStream outObject = null;
	//private ObjectInputStream in = null;
	private Timer thEmiss;
	private Thread thRecep;
	private Emission emiss = null;
	private Reception recep = null;
	private ControleurReception cr;
	
	public ComClientServeur(Socket s, ControleurReception cr){
		socket = s;
		this.cr = cr;
	}
	
	public void stop(){
		if( emiss != null && recep != null ){
			//emiss.stop();
			//recep.stop();
			//thRecep.interrupt();
			thEmiss.cancel();
			try {
				socket.close();
			} catch (IOException e) {
				if( Debug.isEnable() )
				System.err.println( "Erreur fermeture socket lors de la demande d'arret : "+e.getMessage() );
			}
		}
	}
	
	public void run() {
		try {
			outObject = new ObjectOutputStream(socket.getOutputStream());
			//in = new ObjectInputStream(socket.getInputStream());
						
			emiss = new Emission( outObject );
			thEmiss = new Timer();
			thEmiss.scheduleAtFixedRate(emiss, 0, 75);
			
			recep = new Reception(socket, cr);
			thRecep = new Thread( recep );
			thRecep.setDaemon(true);
			thRecep.start();
 
		} catch (IOException e) {
			System.err.println("Erreur lors de l'établissement de la connexion");
		}
	}

}