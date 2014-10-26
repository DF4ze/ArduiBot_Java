package vues.panels;

import java.net.MalformedURLException;

import javax.swing.JPanel;

import modeles.CtrlCat;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import controleurs.Debug;

public class CamPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	static {
		Webcam.setDriver(new IpCamDriver());
	}

	
	public CamPanel(CtrlCat oModel){
		String cam = oModel.getSelectedDevice();
		WebcamPanel panel = null;
		
		if( cam.equals("local") ){
			Webcam webcam = Webcam.getDefault();
			if( webcam != null )
				panel = new WebcamPanel(webcam);
			else
				if( Debug.isEnable() )
					System.out.println("Cam Local HS");
			//panel = new WebcamPanel(Webcam.getDefault());
			
		}else{
			String name = cam;
			String url = cam;			
			IpCamMode mode = IpCamMode.PUSH;
			IpCamDevice myIpCam = null;
			
			try {
				myIpCam = new IpCamDevice(name, url, mode);
				IpCamDeviceRegistry.register(myIpCam);
				panel = new WebcamPanel(Webcam.getWebcams().get(0));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if( panel != null ){
			panel.setFillArea(true);
			panel.setFPSLimit(1);
			add(panel);
		}
	}

}
