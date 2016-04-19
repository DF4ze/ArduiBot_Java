package demos.sockets.d_tchat.serveur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Authentification implements Runnable {

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private String login = "zero", pass =  null;
	public boolean authentifier = false;
	public Thread t2;
	
	public Authentification(Socket s){
		 socket = s;
		}
	public void run() {
	
		try {
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
		while(!authentifier){
			
			out.println("Entrez votre login :");
			out.flush();
			login = in.readLine();
			
			
			out.println("Entrez votre mot de passe :");
			out.flush();
			pass = in.readLine();

			if(isValid(login, pass)){
				
				out.println("connecte");
				System.out.println(login +" vient de se connecter ");
				out.flush();
				authentifier = true;	
			}
			else {out.println("erreur"); out.flush();}
		 }
			t2 = new Thread(new Chat_ClientServeur(socket,login));
			t2.start();
			
		} catch (IOException e) {
			
			System.err.println(login+" ne r�pond pas !");
		}
	}
	
	@SuppressWarnings("unused")
	private static boolean isValid(String login, String pass) {
		
		
		boolean connexion = false;
		Scanner sc = null;
		try {
			 sc = new Scanner(new File("zero.txt"));
			
			
			while(sc.hasNext()){
				if(sc.nextLine().equals(login+" "+pass)){
              	  connexion=true;
				  break;
				}
             }
			
			
			
		} catch (FileNotFoundException e) {	
			System.err.println("Le fichier n'existe pas !");
		}finally{
			if( sc != null )
				sc.close();
		}
	return true;//connexion;
		
	}

}