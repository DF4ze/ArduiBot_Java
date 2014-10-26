package vues.panels;

import java.net.MalformedURLException;

import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

public class CamPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	static {
		Webcam.setDriver(new IpCamDriver());
	}

	
	public CamPanel(){
		String name = "Test255";
		String url = "http://192.168.1.33:8080/?action=stream";
		IpCamMode mode = IpCamMode.PUSH;
		IpCamDevice myIpCam = null;
		
		try {
			myIpCam = new IpCamDevice(name, url, mode);
			IpCamDeviceRegistry.register(myIpCam);
			//daCam = IpCamDeviceRegistry.register(myIpCam);
			
			WebcamPanel panel = new WebcamPanel(Webcam.getDefault());
			
			
			if( Webcam.getWebcams().size() == 0 ){
				System.out.println("Il n'y a pas de WebCam...");
				
				
			}else{
				//WebcamPanel panel = new WebcamPanel(Webcam.getWebcams().get(0));
//				panel.setFPSDisplayed(true);
				panel.setFillArea(true);
				panel.setFPSLimit(1);
				add(panel);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
