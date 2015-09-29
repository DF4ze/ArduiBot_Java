package modeles.catalogues;

import java.util.HashMap;
import java.util.Observable;

import controleurs.Debug;

public class CtrlCat extends Observable {
		
	private boolean directionEnable = false;
	private boolean tourelleEnable = false;
	private boolean cameraEnable = false;
	private boolean extraEnable = false;
	private boolean takePictureEnable = false;
	private boolean takeVideoEnable = false;
	
	private boolean playCamBtnState = true;
	
	private boolean reverseYDir = false;
	private boolean reverseYTour = false;

	private boolean lightCheck = false;
	private boolean strobCheck = false;
	private boolean lazerCheck = false;
	private boolean standByCheck = true;
	private boolean webCamService = false;

	private boolean reduceCtrl = true;
	
	//private HashMap<Integer, Integer> distances = new HashMap<Integer, Integer>();
	
	private float voltage = 0;
	
	
	private PilotCat oModGraph;

	public CtrlCat( PilotCat oModGraph ) {
		this.oModGraph = oModGraph;
	}



	public boolean isDirectionEnable() {
		return directionEnable;
	}
	public void setDirectionEnable(boolean directionEnable) {
		if( Debug.isEnable() )
			System.out.println("Model : SetDirectionEnable: "+directionEnable);

		this.directionEnable = directionEnable;
		oModGraph.setDirectionEnable(directionEnable);
		
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
		oModGraph.setTourelleEnable(tourelleEnable);
		
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

		//setDirectionEnable(cameraEnable);
		//setTourelleEnable(cameraEnable);
		//setExtraEnable(cameraEnable);
		setTakePictureEnable(cameraEnable);
		setTakeVideoEnable(cameraEnable);
		setPlayCamBtnState(!cameraEnable);
		
		
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
		
		if( !standByCheck ){
			setDirectionEnable(true);
			setTourelleEnable(true);
		}
			
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
	
	public boolean isReverseYDir() {
		return reverseYDir;
	}
	public void setReverseYDir(boolean reverseYDir) {
		this.reverseYDir = reverseYDir;

		setChanged();
		notifyObservers("REVERSEYDIR");
	}

	public boolean isReverseYTour() {
		return reverseYTour;
	}
	public void setReverseYTour(boolean reverseYTour) {
		this.reverseYTour = reverseYTour;

		setChanged();
		notifyObservers("REVERSEYTOUR");
	}



	public boolean isLightCheck() {
		return lightCheck;
	}
	public void setLightCheck(boolean lightCheck) {
		this.lightCheck = lightCheck;
		
		setChanged();
		notifyObservers("LIGHTCHECK");
	}

	public boolean isStrobCheck() {
		return strobCheck;
	}
	public void setStrobCheck(boolean strobCheck) {
		this.strobCheck = strobCheck;
		
		setChanged();
		notifyObservers("STROBCHECK");
	}

	public boolean isLazerCheck() {
		return lazerCheck;
	}
	public void setLazerCheck(boolean lazerCheck) {
		this.lazerCheck = lazerCheck;
		
		setChanged();
		notifyObservers("LAZERCHECK");
	}

	public boolean isStandByCheck() {
		return standByCheck;
	}
	public void setStandByCheck(boolean standByCheck) {
		if( standByCheck != this.standByCheck ){
			setTourelleEnable(!standByCheck);
			setDirectionEnable(!standByCheck);
			
			this.standByCheck = standByCheck;
			setChanged();
			notifyObservers("STANDBYCHECK");
		}
	}

	public boolean isWebCamService() {
		return webCamService;
	}
	public void setWebCamService(boolean webCamService) {
		this.webCamService = webCamService;
		
		setChanged();
		notifyObservers("WEBCAMSERVICE");
	}
	
	public boolean isReduceCtrl() {
		return reduceCtrl;
	}
	public void setReduceCtrl(boolean reduceCtrl) {
		this.reduceCtrl = reduceCtrl;
		
		setChanged();
		notifyObservers("REDUCECTRL");

	}


	public boolean isPlayCamBtnState() {
		return playCamBtnState;
	}

	public void setPlayCamBtnState(boolean playCamBtnState) {
		this.playCamBtnState = playCamBtnState;
		
		setChanged();
		notifyObservers("PLAYCAM");
	}


	public void setDistances(HashMap<Integer, Float> distances) {
		//this.distances = distances;
		
		setChanged();
		notifyObservers( distances );
	}



	public float getVoltage() {
		return voltage;
	}
	public void setVoltage(float voltage) {
		this.voltage = voltage;
		
		setChanged();
		notifyObservers("VOLTAGE");
	}


}
