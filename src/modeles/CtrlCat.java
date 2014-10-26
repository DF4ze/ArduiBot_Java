package modeles;

import java.util.Observable;

import controleurs.Debug;

public class CtrlCat extends Observable {
	
	private boolean directionEnable = false;
	private boolean tourelleEnable = false;
	private boolean cameraEnable = false;
	private boolean extraEnable = false;
	
	

	public CtrlCat() {
		// TODO Auto-generated constructor stub
	}



	public boolean isDirectionEnable() {
		return directionEnable;
	}



	public void setDirectionEnable(boolean directionEnable) {
		if( Debug.isEnable() )
			System.out.println("SetDirectionEnable: "+directionEnable);

		this.directionEnable = directionEnable;
		
		setChanged();
		notifyObservers();
	}



	public boolean isTourelleEnable() {
		return tourelleEnable;
	}



	public void setTourelleEnable(boolean tourelleEnable) {
		this.tourelleEnable = tourelleEnable;
	}



	public boolean isCameraEnable() {
		return cameraEnable;
	}



	public void setCameraEnable(boolean cameraEnable) {
		this.cameraEnable = cameraEnable;
	}



	public boolean isExtraEnable() {
		return extraEnable;
	}



	public void setExtraEnable(boolean extraEnable) {
		this.extraEnable = extraEnable;
	}

}
