package modeles;

import java.util.Observable;

import controleurs.Debug;

public class CtrlCat extends Observable {
		
	private boolean directionEnable = false;
	private boolean tourelleEnable = false;
	private boolean cameraEnable = false;
	private boolean extraEnable = false;
	private boolean takePictureEnable = false;
	private boolean takeVideoEnable = false;
	

	public CtrlCat() {
		// TODO Auto-generated constructor stub
	}



	public boolean isDirectionEnable() {
		return directionEnable;
	}



	public void setDirectionEnable(boolean directionEnable) {
		if( Debug.isEnable() )
			System.out.println("Model : SetDirectionEnable: "+directionEnable);

		this.directionEnable = directionEnable;
		
		setChanged();
		notifyObservers("DIRECTIONENABLE");
	}



	public boolean isTourelleEnable() {
		return tourelleEnable;
	}
	public void setTourelleEnable(boolean tourelleEnable) {
		if( Debug.isEnable() )
			System.out.println("Model : SetTourelleEnable: "+tourelleEnable);
		
		this.tourelleEnable = tourelleEnable;
		
		setChanged();
		notifyObservers("TOURELLEENABLE");
	}



	public boolean isCameraEnable() {
		return cameraEnable;
	}
	public void setCameraEnable(boolean cameraEnable) {
		if( Debug.isEnable() )
			System.out.println("Model : setCameraEnable: "+cameraEnable);
		
		this.cameraEnable = cameraEnable;

		setDirectionEnable(cameraEnable);
		setTourelleEnable(cameraEnable);
		setExtraEnable(cameraEnable);
		setTakePictureEnable(cameraEnable);
		setTakeVideoEnable(cameraEnable);

		setChanged();
		notifyObservers("CAMERAENABLE");
	}



	public boolean isExtraEnable() {
		return extraEnable;
	}
	public void setExtraEnable(boolean extraEnable) {
		if( Debug.isEnable() )
			System.out.println("Model : setExtraEnable: "+extraEnable);

		this.extraEnable = extraEnable;
		setChanged();
		notifyObservers("EXTRAENABLE");
	}



	public boolean isTakePictureEnable() {
		return takePictureEnable;
	}
	public void setTakePictureEnable(boolean takePhotoEnable) {
		this.takePictureEnable = takePhotoEnable;
		
		setChanged();
		notifyObservers("TAKEPICTUREENABLE");

	}



	public boolean isTakeVideoEnable() {
		return takeVideoEnable;
	}
	public void setTakeVideoEnable(boolean takeVideoEnable) {
		this.takeVideoEnable = takeVideoEnable;
		
		setChanged();
		notifyObservers("TAKEVIDEOENABLE");

	}

}
