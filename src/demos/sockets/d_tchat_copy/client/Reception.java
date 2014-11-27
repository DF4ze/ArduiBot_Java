package demos.sockets.d_tchat_copy.client;
import java.io.BufferedReader;
import java.io.IOException;


public class Reception implements Runnable {

	private BufferedReader in;
	private String message = null;
	
	public Reception(BufferedReader in){
		
		this.in = in;
	}
	
	public void run() {
		
		while(true){
	        try {
	        	
			message = in.readLine();
			
			if( message == null )
				throw new IOException();
			
			System.out.println("Le serveur vous dit :" +message);
			
		    } catch (IOException e) {
				System.out.println("Le serveur s'est deconnecté");
				break;
				//e.printStackTrace();
			}
		}
	}

}