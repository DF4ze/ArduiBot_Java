package modeles.entites;

import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;

public class Profile {

	private IpCamDevice webcam;
	private Connexion connexion;
	
	public Profile(){
		
	}

	public IpCamDevice getWebcam() {
		return webcam;
	}

	public void setWebcam(IpCamDevice webcam) {
		this.webcam = webcam;
	}

	public Connexion getConnexion() {
		return connexion;
	}

	public void setConnexion(Connexion connexion) {
		this.connexion = connexion;
	}

}
