package modeles.catalogues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import org.json.simple.parser.ParseException;

import controleurs.Debug;
import modeles.dao.filerecord.FileSocketStream;
import modeles.entites.SocketModel;

public class SocketCat extends Observable{
	
	private String name;
	private ArrayList<SocketModel> sockets;
	private Integer selected = 0;

	public static final String FILESOCKS = "configs/listSocks.json";

	public SocketCat() {
		sockets = new ArrayList<SocketModel>();
		
		try {
			readSockets();
		} catch (IOException | ParseException e) {
			if( Debug.isEnable() )
				System.err.println("Erreur lors du chargement du fichier de Sockets");
		}
	}

	public ArrayList<SocketModel> getSockets() {
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
		setSelected(0);
		
		setChanged();
		notifyObservers("SOCKETDELETED");
	}
	public void delSocket( int pro ){
		sockets.remove(pro);
		setSelected(0);
		
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

	public void readSockets() throws IOException, ParseException{
		 sockets = FileSocketStream.jsonToSocketCat(FILESOCKS);
		 
		setChanged();
		notifyObservers("SOCKETADDED");
	}
	
	public void writeSockets() throws IOException, ParseException{
		 FileSocketStream.socketCatToJSON(this, FILESOCKS);	
	}

	public Integer getSelected() {
		return selected;
	}

	public SocketModel getSelectedSocket() {
		if( selected != null )
			return sockets.get(selected);
		else
			return null;
	}

	public void setSelected(int selected ) {
		this.selected = selected;
	}
}
