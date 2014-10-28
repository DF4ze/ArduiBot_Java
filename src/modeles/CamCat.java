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

import controleurs.Debug;

public class CamCat extends Observable{
	
	private ArrayList<String> cams = new  ArrayList<String>();
	private ArrayList<IpCamDevice> devices = new ArrayList<IpCamDevice>();
	private int indexSelectedDevice = 0;
	
	public static final String FILECAM = "ipcams/listIPCams.properties";
	
    static {
        Webcam.setDriver(new WebcamCompositeDriver(new WebcamDefaultDriver(), new IpCamDriver()));
    }
    
    
    
	public CamCat(){
		// savoir s'il y a la cam locale de connect�
		if( Webcam.getDefault() != null ) {
			cams.add("local");
		} 
		
		// R�cup�rer les IP des cam distantes
		try {
			this.cams.addAll( FileIPCamReader.readCams() );
		
			// les transformer en DEVICE
			for( String url : cams ){
				if( url.equals("local") )
					devices.add(null);
				else{
					// on tente la cr�ation d'un IPDEVICE
					try {
						IpCamDevice myIpCam = new IpCamDevice( url, url, IpCamMode.PUSH);
						devices.add( myIpCam );
						// les ajouter au registre
						IpCamDeviceRegistry.register( myIpCam );
					} catch (MalformedURLException e) {
						if( Debug.isEnable() )
							e.printStackTrace();
					}
	
				}
			}
		} catch (FileNotFoundException e1) {
			if( Debug.isEnable() )
				e1.printStackTrace();
		}

		
	}



	public ArrayList<String> getCams() {
		return cams;
	}
	public String[] getArrayCams() {
		String[] retour = new String[ cams.size() ];
		int i=0;
		for( String cam : cams )
			retour[i++] = cam;
		return retour;
	}

	
	public ArrayList<IpCamDevice> getDevices() {
		return devices;
	}
	



//	public String getSelectedCam() {
//		return selectedCam;
//	}
	public void setSelectedCam(String selectedCam) {
		indexSelectedDevice = cams.indexOf( selectedCam );
	}

	public int getIndexSelectedDevice(){
		return indexSelectedDevice;
	}
	
	
	public void saveCams(){
		FileIPCamReader.writeIPCamToProperties(devices);
	}
}
