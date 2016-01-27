package controleurs.audio;

import modeles.catalogues.SocketCat;
import modeles.entites.MicrophoneModel;
import controleurs.socketclient.mic.SocketClientMicro;

public class MicroStream {

	private MicroStream() {
		// TODO Auto-generated constructor stub
	}
	
	public static void start( SocketCat sm ){
		SocketClientMicro sc = new SocketClientMicro( sm );
		sc.start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StreamedMicPlayer sp = new StreamedMicPlayer();
		sp.start();
	}
	public static void stop(){
		MicrophoneModel.connected = false;
	}


}
