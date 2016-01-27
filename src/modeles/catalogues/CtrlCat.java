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
	
	private boolean recoVocEnable = false;
	private boolean ttsEnable = false;
	private boolean distantSoundEnable = false;
	private boolean distantSoundMute = false;
	
	private boolean playCamBtnState = true;
	
	private boolean reverseYDir = false;
	private boolean reverseYTour = true;

	private boolean lightCheck = false;
	private boolean strobCheck = false;
	private boolean lazerCheck = false;
	private boolean standByCheck = true;
	private boolean webCamService = false;

	private boolean recoVocCheck = false;
	private boolean sonLocalCheck = true;
	private boolean sonDistantCheck = false;

	public static final int ttsPico = 1;
	public static final int ttsEspeak = 2;
	private String textToSay = "";
	private int ttsSelected = ttsPico;
	
	private boolean streamedMic = false;
	private boolean streamMicEnable = false;
	
	private boolean reduceCtrl = true;
	private boolean reduceOpts = true;
		
	private float voltage = 0;
	private String wifiSignal = "N/A";
	private String wifiQuality = "N/A";
	private String wifiNoise = "N/A";
	
	
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



	public boolean isReduceOpts() {
		return reduceOpts;
	}

	public void setReduceOpts(boolean reduceOpts) {
		this.reduceOpts = reduceOpts;
		
		setChanged();
		notifyObservers("REDUCEOPTS");
	}



	public boolean isRecoVocCheck() {
		return recoVocCheck;
	}

	public void setRecoVocCheck(boolean recoVocCheck) {
		this.recoVocCheck = recoVocCheck;
		
		setChanged();
		notifyObservers("RECOVOCCHECK");
	}



	public boolean isSonLocalCheck() {
		return sonLocalCheck;
	}

	public void setSonLocalCheck(boolean sonLocalCheck) {
		this.sonLocalCheck = sonLocalCheck;
		
		setChanged();
		notifyObservers("SONLOCALCHECK");
	}



	public boolean isSonDistantCheck() {
		return sonDistantCheck;
	}

	public void setSonDistantCheck(boolean sonDistantCheck) {
		this.sonDistantCheck = sonDistantCheck;
		
		setChanged();
		notifyObservers("SONDISTANTCHECK");
	}


	public boolean isRecoVocEnable() {
		return recoVocEnable;
	}
	
	public void setRecoVocEnable(boolean recoVocEnable) {
		this.recoVocEnable = recoVocEnable;
		
		setChanged();
		notifyObservers("RECOVOCENABLE");
	}


	public String getTextToSay() {
		return textToSay;
	}
	
	public void setTextToSay(String textToSay) {
		this.textToSay = textToSay;
	}

	
	public int getTtsSelected() {
		return ttsSelected;
	}
	
	public void setTtsSelected(int ttsSelected) {
		this.ttsSelected = ttsSelected;
	}


	public boolean isTtsEnable() {
		return ttsEnable;
	}

	public void setTtsEnable(boolean ttsEnable) {
		this.ttsEnable = ttsEnable;
		
		setChanged();
		notifyObservers("TTSENABLE");
	}


	public boolean isDistantSoundEnable() {
		return distantSoundEnable;
	}

	public void setDistantSoundEnable(boolean distantSoundEnable) {
		this.distantSoundEnable = distantSoundEnable;
		setChanged();
		notifyObservers("DISTANTSOUNDENABLE");
	}

	public boolean isDistantSoundMute() {
		return distantSoundMute;
	}

	
	public void setDistantSoundMute(boolean distantSoundMute) {
		this.distantSoundMute = distantSoundMute;
	}

	
	
	public boolean isStreamedMic() {
		return streamedMic;
	}

	public void setStreamedMic(boolean streamedMic) {
		this.streamedMic = streamedMic;
		
		setChanged();
		notifyObservers("STREAMMIC");
	}
	public void setStreamMicEnable(boolean StreamMicEnable) {
		this.streamMicEnable = StreamMicEnable;
		setChanged();
		notifyObservers("STREAMMICENABLE");
	}
	public boolean isStreamMicEnable() {
		return this.streamMicEnable;
	}


	public String getWifiSignal() {
		return wifiSignal;
	}

	public void setWifiSignal(String signalWifi) {
		this.wifiSignal = signalWifi;
		
		setChanged();
		notifyObservers("WIFISIGNAL");
	}



	public String getWifiQuality() {
		return wifiQuality;
	}

	public void setWifiQuality(String wifiQuality) {
		this.wifiQuality = wifiQuality;
		setChanged();
		notifyObservers("WIFIQUALITY");
	}



	public String getWifiNoise() {
		return wifiNoise;
	}

	public void setWifiNoise(String wifiNoise) {
		this.wifiNoise = wifiNoise;
		setChanged();
		notifyObservers("WIFINOISE");
	}
}
