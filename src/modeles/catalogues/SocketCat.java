package modeles.catalogues;

import java.util.ArrayList;
import java.util.Observable;

import modeles.entites.SocketModel;

public class SocketCat extends Observable{
	
	private String name;
	private ArrayList<SocketModel> sockets;

	public SocketCat() {
		sockets = new ArrayList<SocketModel>();
	}

	public ArrayList<?> getSockets() {
		return sockets;
	}
	public String[] getArraySockets() {
		String[] aSm = new String[ sockets.size() ];
		for( int i=0; i < sockets.size(); i++){
			aSm[i] = sockets.get(i).toString();
		}
		
		return aSm;
	}

	public void setSockets(ArrayList<SocketModel> alProfiles) {
		this.sockets = alProfiles;
	}
	
	public void addSocket( SocketModel pro ){
		sockets.add(pro);
		
		setChanged();
		notifyObservers("SOCKETADDED");
	}
	
	public void delSocket( SocketModel pro ){
		sockets.remove(pro);
		
		setChanged();
		notifyObservers("SOCKETDELETED");
	}
	public void delSocket( int pro ){
		sockets.remove(pro);
		
		setChanged();
		notifyObservers("SOCKETDELETED");
	}
	
	public void clear(){
		sockets.clear();
		
		setChanged();
		notifyObservers("SOCKETDELETED");
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
