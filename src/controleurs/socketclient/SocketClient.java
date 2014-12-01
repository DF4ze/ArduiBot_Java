package controleurs.socketclient;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import modeles.dao.communication.ArduiBotServer;
import controleurs.Debug;
import controleurs.socketclient.com.ComClientServeur;

public class SocketClient {

	private Socket socket = null;
	private Thread t1;
	private ArduiBotServer model;
	private ComClientServeur con = null;
	
	public SocketClient( ArduiBotServer model ){
		this.model = model;
	}
	
	public void start() {
		try {
			if( Debug.isEnable() )
				System.out.println("Demande de connexion");
			socket = new Socket( model.getIp(), model.getPort());
//			socket = new Socket("192.168.1.33",2009);
			if( Debug.isEnable() )
				System.out.println("Connexion établie avec le serveur, "); // Si le message s'affiche c'est que je suis connecté
		
			con = new ComClientServeur(socket);
			t1 = new Thread( con );
			t1.setDaemon(true);
			t1.start();
		
		} catch (UnknownHostException e) {
			if( Debug.isEnable() )
				System.err.println("Impossible de se connecter à l'adresse "+socket.getLocalAddress());
		} catch (IOException e) {
			if( Debug.isEnable() )
				System.err.println("Aucun serveur à l'écoute du port 2009");
		}
	}
	
	public void stop(){
		if( con != null )
		con.stop();
	}
}