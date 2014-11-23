package modeles.graphical.camentities;

import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

public class CamMode {

	private IpCamMode mode;
	private String name;
	
	public CamMode(IpCamMode mode, String name) {
		this.mode = mode;
		this.name = name;
	}

	public IpCamMode getMode() {
		return mode;
	}

	public void setMode(IpCamMode mode) {
		this.mode = mode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}

}
