package controleurs.socketclient.com;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ComClientServeur implements Runnable {

	private Socket socket;
	private ObjectOutputStream outObject = null;
	private BufferedReader in = null;
	private Thread thEmiss;
	private Thread thRecep;
	private Emission emiss = null;
	private Reception recep = null;
	
	public ComClientServeur(Socket s){
		socket = s;
	}
	
	public void stop(){
		if( emiss != null && recep != null ){
			emiss.stop();
			//recep.stop();
			//thRecep.interrupt();
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void run() {
		try {
			outObject = new ObjectOutputStream(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						
			emiss = new Emission( outObject );
			thEmiss = new Thread( emiss );
			thEmiss.setDaemon(true);
			thEmiss.start();
			
			recep = new Reception(in);
			thRecep = new Thread( recep );
			thRecep.setDaemon(true);
			thRecep.start();
 
		} catch (IOException e) {
			System.err.println("Le serveur distant s'est déconnecté !");
		}
	}

}