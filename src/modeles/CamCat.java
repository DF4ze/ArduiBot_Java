package modeles;

import java.util.Observable;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamCompositeDriver;
import com.github.sarxos.webcam.ds.buildin.WebcamDefaultDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;

public class CamCat extends Observable{
	
	
    static {
        Webcam.setDriver(new WebcamCompositeDriver(new WebcamDefaultDriver(), new IpCamDriver()));
    }
    
    
	public CamCat() {
		// savoir s'il y a la cam locale de connecté
		// Récupérer les IP des cam distantes
		// les transformer en DEVICE
		// les ajouter au driver
	}

}
