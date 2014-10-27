package modeles;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Observable;

import modeles.reader.FileIPCamReader;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamCompositeDriver;
import com.github.sarxos.webcam.ds.buildin.WebcamDefaultDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

public class CamCat extends Observable{
	
	private ArrayList<String> cams = null;
	private ArrayList<IpCamDevice> devices = null;
	private String selectedDevice = "local";
	
	public static final String FILECAM = "ipcams/listIPCams.txt";
	
    static {
        Webcam.setDriver(new WebcamCompositeDriver(new WebcamDefaultDriver(), new IpCamDriver()));
    }
    
    
    
	public CamCat() throws FileNotFoundException, MalformedURLException {
		// savoir s'il y a la cam locale de connecté
		if( Webcam.getDefault() != null ) {
			cams.add("local");
		} 
		
		// Récupérer les IP des cam distantes
		this.cams.addAll( FileIPCamReader.readCams() );
		
		// les transformer en DEVICE
		for( String url : cams ){
			if( cams.equals("local") )
				devices.add(null);
			else{
				IpCamDevice myIpCam = new IpCamDevice( url, url, IpCamMode.PUSH);
				devices.add( myIpCam );
				// les ajouter au registre
				IpCamDeviceRegistry.register( myIpCam );
			}
		}
		
	}



	public ArrayList<String> getCams() {
		return cams;
	}

	public ArrayList<IpCamDevice> getDevices() {
		return devices;
	}
	



	public String getSelectedDevice() {
		return selectedDevice;
	}
	public void setSelectedDevice(String selectedDevice) {
		this.selectedDevice = selectedDevice;
	}

}
