package modeles.inputs;

import java.util.Observable;

import modeles.DroneActions;

public class KeyCat extends Observable{

	//// Direction
	// statut
	private boolean dirUp = false;
	private boolean dirLeft = false;
	private boolean dirRight = false;
	private boolean dirDown = false;	
	
	//// Tourelle
	// statut
	private boolean tourUp = false;
	private boolean tourLeft = false;
	private boolean tourRight = false;
	private boolean tourDown = false;
	

	private String lastPanel = "none";
	
	public KeyCat() {
		
	}

	public boolean setPressedKey( int code ){
		boolean bFinded = false;
		switch(code){
		case DroneActions.dirUpCode : 
			dirUp = true;
			lastPanel = "dir";
			bFinded = true;
			break;
		case DroneActions.dirLeftCode : 
			dirLeft = true;
			lastPanel = "dir";
			bFinded = true;
			break;
		case DroneActions.dirRightCode : 
			dirRight = true;
			lastPanel = "dir";
			bFinded = true;
			break;
		case DroneActions.dirDownCode : 
			dirDown = true;
			lastPanel = "dir";
			bFinded = true;
			break;
			
		case DroneActions.tourUpCode : 
			tourUp = true;
			bFinded = true;
			lastPanel = "tour";
			break;
		case DroneActions.tourLeftCode : 
			tourLeft = true;
			lastPanel = "tour";
			bFinded = true;
			break;
		case DroneActions.tourRightCode : 
			tourRight = true;
			lastPanel = "tour";
			bFinded = true;
			break;
		case DroneActions.tourDownCode : 
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
		case DroneActions.dirUpCode : 
			dirUp = false;
			bFinded = true;
			break;
		case DroneActions.dirLeftCode : 
			dirLeft = false;
			bFinded = true;
			break;
		case DroneActions.dirRightCode : 
			dirRight = false;
			bFinded = true;
			break;
		case DroneActions.dirDownCode : 
			dirDown = false;
			bFinded = true;
			break;
			
		case DroneActions.tourUpCode : 
			tourUp = false;
			bFinded = true;
			break;
		case DroneActions.tourLeftCode : 
			tourLeft = false;
			bFinded = true;
			break;
		case DroneActions.tourRightCode : 
			tourRight = false;
			bFinded = true;
			break;
		case DroneActions.tourDownCode : 
			tourDown = false;
			bFinded = true;
			break;
			
		default :
			bFinded = false;
			
		}
		
		return bFinded;
	}
	
	public String getDirWay(boolean reverseY){
		String sWay = "";
		if( dirUp ){
			sWay += (reverseY)?DroneActions.downWay:DroneActions.upWay;
			if( dirLeft )
				sWay += DroneActions.leftWay;
			else if( dirRight )
				sWay += DroneActions.rightWay;
			
		}else if( dirDown ){
			sWay += (reverseY)?DroneActions.upWay:DroneActions.downWay;
			if( dirLeft )
				sWay += DroneActions.leftWay;
			else if( dirRight )
				sWay += DroneActions.rightWay;
		}else if( dirLeft )
			sWay += DroneActions.leftWay;
		else if( dirRight )
			sWay += DroneActions.rightWay;
		
		return sWay;
	}

	public String getTourWay(boolean reverseY){
		String sWay = "";
		if( tourUp ){
			sWay += (reverseY)?DroneActions.downWay:DroneActions.upWay;
			if( tourLeft )
				sWay += DroneActions.leftWay;
			else if( tourRight )
				sWay += DroneActions.rightWay;
			
		}else if( tourDown ){
			sWay += (reverseY)?DroneActions.upWay:DroneActions.downWay;
			if( tourLeft )
				sWay += DroneActions.leftWay;
			else if( tourRight )
				sWay += DroneActions.rightWay;
		}else if( tourLeft )
			sWay += DroneActions.leftWay;
		else if( tourRight )
			sWay += DroneActions.rightWay;
		
		return sWay;
	}

	
}
