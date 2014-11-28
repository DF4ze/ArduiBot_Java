package controleurs.socketclient.com;
import java.io.BufferedReader;
import java.io.IOException;


public class Reception implements Runnable {

	private BufferedReader in;
	private String message = null;
	private boolean bRunning = true;
	
	public Reception(BufferedReader in){
		
		this.in = in;
	}
	
	public void stop(){
		bRunning = false;
		in = null;
	}	
	
	public void run() {
		
		while( bRunning ){
	        try {
	        	
			message = in.readLine();
			
			if( message == null )
				throw new IOException();
			
			System.out.println("Le serveur vous dit :" +message);
			
		    } catch (IOException e) {
				System.out.println("Le serveur s'est deconnecté");
				break;
				//e.printStackTrace();
			} catch( NullPointerException e ){
				
			}
		}
	}

}