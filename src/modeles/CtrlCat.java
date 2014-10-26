package modeles;

import java.util.Observable;

import controleurs.Debug;

public class CtrlCat extends Observable {
		
	private boolean directionEnable = false;
	private boolean tourelleEnable = false;
	private boolean cameraEnable = false;
	private boolean extraEnable = false;
	
	private String[] Devices = {"local", "http://192.168.1.33:8080/?action=stream"};
	private String selectedDevice = "local";
	
	

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



	public String[] getDevices() {
		return Devices;
	}
	public void setDevices(String[] devices) {
		Devices = devices;
	}



	public String getSelectedDevice() {
		return selectedDevice;
	}
	public void setSelectedDevice(String selectedDevice) {
		this.selectedDevice = selectedDevice;
	}

}
