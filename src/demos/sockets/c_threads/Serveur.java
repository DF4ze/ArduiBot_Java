package demos.sockets.c_threads;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public static void main(String[] zero){
		
		ServerSocket socket;
		try {
		socket = new ServerSocket(2009);
		Thread t = new Thread(new Accepter_clients(socket));
		t.start();
		System.out.println("Mes employeurs sont pr�ts !");
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}

class Accepter_clients implements Runnable {

	   private ServerSocket socketserver;
	   private Socket socket;
	   private int nbrclient = 1;
		public Accepter_clients(ServerSocket s){
			socketserver = s;
		}
		
		public void run() {

	        try {
	        	while(true){
	        		socket = socketserver.accept(); // Un client se connecte on l'accepte
	                System.out.println("Le client num�ro "+nbrclient+ " est connect� !");
	                nbrclient++;
	                socket.close();
	        	}
	        
	        } catch (IOException e) {
				e.printStackTrace();
			}
		}

	}