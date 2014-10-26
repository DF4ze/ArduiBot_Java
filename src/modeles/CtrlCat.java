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



	public String[] getDevices() {
		return Devices;
	}
	public void setDevices(String[] devices) {
		if( Debug.isEnable() )
			System.out.println("Model : setDevices: "+devices);
		
		Devices = devices;
		setChanged();
		notifyObservers("SETDEVICE");
	}



	public String getSelectedDevice() {
		return selectedDevice;
	}
	public void setSelectedDevice(String selectedDevice) {
		if( Debug.isEnable() )
			System.out.println("Model : setSelectedDevice: "+selectedDevice);

		this.selectedDevice = selectedDevice;
		setChanged();
		notifyObservers("SELECTEDDEVICE");
	}

}
