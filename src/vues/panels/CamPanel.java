package vues.panels;

import java.net.MalformedURLException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import modeles.CtrlCat;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamCompositeDriver;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.buildin.WebcamDefaultDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import controleurs.Debug;
import exceptions.CamException;

public class CamPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private CtrlCat oModel;
	private WebcamPanel panel = null;

    static {
        Webcam.setDriver(new WebcamCompositeDriver(new WebcamDefaultDriver(), new IpCamDriver()));
    }
	
	public CamPanel(CtrlCat oModel) throws CamException{
		this.oModel = oModel;
		this.oModel.addObserver(this);
		
		String cam = oModel.getSelectedDevice();
				
		
		if( cam.equals("local") ){
			Webcam webcam = Webcam.getDefault();
			if( webcam != null )
				panel = new WebcamPanel(webcam);
			
			else{
				throw new CamException( "La caméra locale n'est pas reconnue" );
			}
			
		}else{
//			if( Debug.isEnable() )
//				System.out.println("***Cam SetDriver");
			//Webcam.setDriver(new IpCamDriver());
			
			String name = cam;
			String url = cam;			
			IpCamMode mode = IpCamMode.PUSH;
			IpCamDevice myIpCam = null;
			
			try {
				if( Debug.isEnable() )
					System.out.println("***Cam IpcamDevice");
				myIpCam = new IpCamDevice(name, url, mode);
				if( Debug.isEnable() )
					System.out.println("***Cam Registry");
				IpCamDeviceRegistry.register(myIpCam);
				if( Debug.isEnable() )
					System.out.println("***Cam WebcamPanel");
				panel = new WebcamPanel(Webcam.getWebcams().get(1));
				
			} catch (MalformedURLException e) {
				throw new CamException( "L'URL n'est pas correcte" );
			}
		}

		if( panel != null ){
			panel.setFillArea(true);
			panel.setFPSLimit(1);
			if( Debug.isEnable() )
				System.out.println("***Cam add");
			add(panel);
			

		}
	}

	public void resetCam(){
		if( panel != null )
			panel.stop();
		//Webcam.resetDriver();
		//IpCamDeviceRegistry.unregisterAll();
	}

	@Override
	public void update(Observable o, Object message) {
		if( o == oModel ){
//			if( message.equals("CAMERAENABLE") ){
//				IpCamDeviceRegistry.unregisterAll();
//				
//				if( Debug.isEnable() )
//					System.out.println("CamPanel: cam unregistred");
//			}
		}
		
	}

}
