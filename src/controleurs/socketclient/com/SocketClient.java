package controleurs.socketclient.com;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import modeles.catalogues.SocketCat;
import controleurs.ControleurReception;
import controleurs.Debug;

public class SocketClient {

	private Socket socket = null;
	private Thread t1;
	private SocketCat model;
	private ComClientServeur con = null;
	private ControleurReception cr;
	private SocketCat oModSock;
	
	public SocketClient( SocketCat model, ControleurReception cr, SocketCat oModSock ){
		this.model = model;
		this.cr = cr;
		this.oModSock = oModSock;
	}
	
	public boolean start() {
		boolean bOk = true;
		try {
			if( Debug.isEnable() )
				System.out.println("Demande de connexion");
			socket = new Socket( model.getSelectedSocket().getIp(), model.getSelectedSocket().getPort());
			if( Debug.isEnable() )
				System.out.println("Connexion établie avec le serveur, "); // Si le message s'affiche c'est que je suis connecté
		
			con = new ComClientServeur(socket, cr, oModSock);
			t1 = new Thread( con );
			t1.setDaemon(true);
			t1.start();
		
		} catch (UnknownHostException e) {
			if( Debug.isEnable() )
				System.err.println("Impossible de se connecter à l'adresse "+socket.getLocalAddress());
			bOk = false;
		} catch (IOException e) {
			if( Debug.isEnable() )
				System.err.println("Aucun serveur à l'écoute du port "+model.getSelectedSocket().getPort());
			bOk = false;
		}
		
		oModSock.setConnected(bOk);
		
		return bOk;
	}
	
	public void stop(){
		if( con != null )
		con.stop();
	}
}