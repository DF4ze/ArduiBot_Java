package demos.ipCamDemo;

import java.awt.BorderLayout;
import java.net.MalformedURLException;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamAuth;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

public class IPCamViewerPush2 {
	static {
		Webcam.setDriver(new IpCamDriver());
	}

	public static void main(String[] args) throws MalformedURLException {
		
		
		// cam0
		String name = "Test255";
		String url = "http://192.168.1.33:8080/?action=stream";
		IpCamMode mode = IpCamMode.PUSH;
		IpCamDevice myIpCam = new IpCamDevice(name, url, mode);
		
		IpCamDeviceRegistry.register(myIpCam);
		
		// cam1
		name = "Test256";
		url = "http://ce3014.myfoscam.org:20054/videostream.cgi";
		IpCamAuth auth = new IpCamAuth("user", "user");
		IpCamDeviceRegistry.register(name, url, mode, auth);
		
		WebcamPanel panel1 = new WebcamPanel(Webcam.getWebcams().get(0));
		WebcamPanel panel2 = new WebcamPanel(Webcam.getWebcams().get(1));
		panel1.setFPSLimit(1);
		JFrame f = new JFrame("Test #255 PUSH");
		
		panel1.setFPSDisplayed(true);
		panel2.setFPSDisplayed(true);
		
		f.setLayout(new BorderLayout());
		f.add(panel1, BorderLayout.EAST);
		f.add(panel2, BorderLayout.WEST);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
