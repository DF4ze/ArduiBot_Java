package demos.sockets.d_tchat_copy.client;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import modeles.dao.communication.beanssend.ExtraAction;
import modeles.dao.communication.beanssend.IAction;


public class Emission implements Runnable {

	private ObjectOutputStream out;
//	private String message = null;
	private Scanner sc = null;
	
	public Emission(ObjectOutputStream out) {
		this.out = out;
		
	}

	
	public void run() {
		
		  sc = new Scanner(System.in);
		  
		  while(true){
			    System.out.println("Appuyer sur entrer pour envoyer un Objet :");
				sc.nextLine();
//				message = sc.nextLine();
				
//				DirectionAction da = new DirectionAction(100, 0, 3);
				ExtraAction da = new ExtraAction(IAction.Light, IAction.On, IAction.prioHight);
				try {
					out.writeObject(da);
				    out.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//out.println(message);
			  }
	}
}