package demos.sockets.d_tchat.serveur;
import java.io.BufferedReader;
import java.io.IOException;


public class Reception implements Runnable {

	private BufferedReader in;
	private String message = null, login = null;
	
	public Reception(BufferedReader in, String login){
		
		this.in = in;
		this.login = login;
	}
	
	public void run() {
		
		while(true){
	        try {
	        	
			message = in.readLine();
			System.out.println(login+" : "+message);
			
		    } catch (IOException e) {
				System.out.println("Le client "+login+" s'est d�co");
				//e.printStackTrace();
				
				break;
			}
		}
	}

}