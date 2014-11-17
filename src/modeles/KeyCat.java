package modeles;

import java.util.Observable;

public class KeyCat extends Observable {

	//// Direction
	// code
	public final int dirUpCode = 90;		// Z
	public final int dirLeftCode = 81;		// Q
	public final int dirRightCode = 68;		// D
	public final int dirDownCode = 83;		// S
	// statut
	private boolean dirUp = false;
	private boolean dirLeft = false;
	private boolean dirRight = false;
	private boolean dirDown = false;	
	
	//// Tourelle
	// code
	public final int tourUpCode = 38;		// fleche haut
	public final int tourLeftCode = 37;		// gauche
	public final int tourRightCode = 39;	// ...
	public final int tourDownCode = 40;		// ...
	// statut
	private boolean tourUp = false;
	private boolean tourLeft = false;
	private boolean tourRight = false;
	private boolean tourDown = false;
	
	//// string d'appel
	private final String upWay = "up";
	private final String leftWay = "left";
	private final String rightWay = "right";
	private final String downWay = "down";

	private String lastPanel = "none";
	
	public KeyCat() {
		
	}

	public boolean setPressedKey( int code ){
		boolean bFinded = false;
		switch(code){
		case dirUpCode : 
			dirUp = true;
			lastPanel = "dir";
			bFinded = true;
			break;
		case dirLeftCode : 
			dirLeft = true;
			lastPanel = "dir";
			bFinded = true;
			break;
		case dirRightCode : 
			dirRight = true;
			lastPanel = "dir";
			bFinded = true;
			break;
		case dirDownCode : 
			dirDown = true;
			lastPanel = "dir";
			bFinded = true;
			break;
			
		case tourUpCode : 
			tourUp = true;
			bFinded = true;
			lastPanel = "tour";
			break;
		case tourLeftCode : 
			tourLeft = true;
			lastPanel = "tour";
			bFinded = true;
			break;
		case tourRightCode : 
			tourRight = true;
			lastPanel = "tour";
			bFinded = true;
			break;
		case tourDownCode : 
			tourDown = true;
			lastPanel = "tour";
			bFinded = true;
			break;
			
		default :
			lastPanel = "none";
			bFinded = false;
			
		}
		
		return bFinded;
	}
	
	public String getLastPanel(){
		return lastPanel;
	}
	
	public boolean setReleasedKey( int code ){
		boolean bFinded = false;
		switch(code){
		case dirUpCode : 
			dirUp = false;
			bFinded = true;
			break;
		case dirLeftCode : 
			dirLeft = false;
			bFinded = true;
			break;
		case dirRightCode : 
			dirRight = false;
			bFinded = true;
			break;
		case dirDownCode : 
			dirDown = false;
			bFinded = true;
			break;
			
		case tourUpCode : 
			tourUp = false;
			bFinded = true;
			break;
		case tourLeftCode : 
			tourLeft = false;
			bFinded = true;
			break;
		case tourRightCode : 
			tourRight = false;
			bFinded = true;
			break;
		case tourDownCode : 
			tourDown = false;
			bFinded = true;
			break;
			
		default :
			bFinded = false;
			
		}
		
		return bFinded;
	}
	
	public String getDirWay(){
		String sWay = "";
		if( dirUp ){
			sWay += upWay;
			if( dirLeft )
				sWay += leftWay;
			else if( dirRight )
				sWay += rightWay;
			
		}else if( dirDown ){
			sWay += downWay;
			if( dirLeft )
				sWay += leftWay;
			else if( dirRight )
				sWay += rightWay;
		}else if( dirLeft )
			sWay += leftWay;
		else if( dirRight )
			sWay += rightWay;
		
		return sWay;
	}

	public String getTourWay(){
		String sWay = "";
		if( tourUp ){
			sWay += upWay;
			if( tourLeft )
				sWay += leftWay;
			else if( tourRight )
				sWay += rightWay;
			
		}else if( tourDown ){
			sWay += downWay;
			if( tourLeft )
				sWay += leftWay;
			else if( tourRight )
				sWay += rightWay;
		}else if( tourLeft )
			sWay += leftWay;
		else if( tourRight )
			sWay += rightWay;
		
		return sWay;
	}

	
}
