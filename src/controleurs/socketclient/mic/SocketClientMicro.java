package controleurs.socketclient.mic;

import java.io.IOException;
import java.net.Socket;

import modeles.catalogues.SocketCat;
import modeles.entites.MicrophoneModel;
import controleurs.Debug;

public class SocketClientMicro {
	private Socket socket;
	private SocketCat model;
	
	public SocketClientMicro(SocketCat model) {
		this.model = model;
	}

	public void start(){

		try {
			socket = new Socket( model.getSelectedSocket().getIp() , model.getMicPort() );
		
			if( Debug.isEnable() )
				System.out.println("Connexion �tablie avec le serveur micro "); 
		
			MicrophoneModel.connected = true;
			MicGrabber sg = new MicGrabber(socket);
			sg.start();
			
		} catch ( IOException e ) {
        	MicrophoneModel.connected = false;

        	if( Debug.isEnable() )
        		System.err.println("Impossible de se connecter au micro : "+e.getMessage());
		}
	}
}
