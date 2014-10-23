package demos.ipCamDemo;

import java.net.MalformedURLException;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

public class IPCamViewerPush {

	static {
		Webcam.setDriver(new IpCamDriver());
	}

	public static void main(String[] args) throws MalformedURLException {
		String name = "Test255";
		String url = "http://192.168.1.33:8080/?action=stream";
		IpCamMode mode = IpCamMode.PUSH;
		IpCamDevice myIpCam = new IpCamDevice(name, url, mode);
		
		IpCamDeviceRegistry.register(myIpCam);
		
//		String url = "http://ce3014.myfoscam.org:20054/videostream.cgi";
//		IpCamAuth auth = new IpCamAuth("user", "user");
//		IpCamDeviceRegistry.register(name, url, mode, auth);
		
		WebcamPanel panel = new WebcamPanel(Webcam.getWebcams().get(0));
		panel.setFPSLimit(1);
		JFrame f = new JFrame("Test #255 PUSH");
		f.add(panel);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
