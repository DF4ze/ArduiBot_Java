package vues.panels;

import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modeles.CtrlCat;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import controleurs.Debug;
import exceptions.CamException;

public class CamPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private CtrlCat oModel;
	private WebcamPanel panel;
	
	static {
		Webcam.setDriver(new IpCamDriver());
	}

	
	public CamPanel(CtrlCat oModel) throws CamException{
		this.oModel = oModel;
		this.oModel.addObserver(this);
		
		String cam = oModel.getSelectedDevice();
		panel = null;
		
		if( cam.equals("local") ){
			Webcam webcam = Webcam.getDefault();
			if( webcam != null )
				panel = new WebcamPanel(webcam);
			else{
				if( Debug.isEnable() )
					System.out.println("Cam Local HS");
				
				throw new CamException( "La caméra locale n'est pas reconnue" );
			}
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
				throw new CamException( "L'URL n'est pas correcte" );
			}
		}

		if( panel != null ){
			panel.setFillArea(true);
			panel.setFPSLimit(1);
			add(panel);
			
			

		}
	}



	@Override
	public void update(Observable o, Object message) {
		if( o == oModel ){
			if( message.equals("SELECTEDDEVICE") ){
				IpCamDeviceRegistry.unregisterAll();
				
				if( Debug.isEnable() )
					System.out.println("CamPanel: cam unregistred");
			}
		}
		
	}

}
