package demos.properties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import modeles.graphical.CamCat;

import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;

public class MyProp {

	public MyProp() {
		// TODO Auto-generated constructor stub
	}
	
	public static void writeIPCamToProperties( ArrayList<IpCamDevice> devices){
	Properties prop = new Properties();
	OutputStream output = null;
 
	try {
 
		output = new FileOutputStream(CamCat.FILECAM);
 
		for( IpCamDevice device : devices ){
			// set the properties value
			prop.setProperty("name", device.getName());
			prop.setProperty("URL", device.getURL().toString());
			prop.setProperty("mode", device.getMode().toString());
			prop.setProperty("resolution_height", Integer.toString(device.getResolution().height));
			prop.setProperty("resolution_width", Integer.toString(device.getResolution().width));
	 
			// save properties to project root folder
			prop.store(output, null);
		}
 
	} catch (IOException io) {
		io.printStackTrace();
	} finally {
		if (output != null) {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}		
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
