package controleurs.socketclient.com;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Observable;

import modeles.catalogues.SocketCat;
import modeles.dao.communication.beansinfos.IInfo;
import controleurs.ControleurReception;
import controleurs.Debug;


public class Reception extends Observable implements Runnable {

	private ObjectInputStream inObject;
	private Socket so;
	private boolean bRunning = true;
	private SocketCat oModSock;
	
	public Reception( Socket so, ControleurReception cr, SocketCat oModSock ){	
		this.so = so;
		this.oModSock = oModSock;
		addObserver(cr);
	}
	
	public void stop(){
		bRunning = false;
		inObject = null;
	}	
	
	public void run() {
		
		while( bRunning ){
	        try {
	        	inObject = new ObjectInputStream(so.getInputStream());
				Object object = inObject.readObject();
				
				if( object instanceof IInfo ){
					setChanged();
					notifyObservers(object);
				}else
					throw new ClassNotFoundException("Pas IINFO");
				
		    } catch (IOException e) {
				System.err.println("Le serveur s'est deconnecté : "+e.getMessage());
				//e.printStackTrace();
				break;
				//e.printStackTrace();
			} catch( NullPointerException e ){
				
			} catch (ClassNotFoundException e) {
				if( Debug.isEnable() )
					System.err.println("Reception Socket : Type de classe non reconnue : "+e.getMessage());
			}
		}
		oModSock.setConnected(false);
		if( Debug.isEnable() )
			System.out.println("Sortie propre du Thread Reception");
	}

}