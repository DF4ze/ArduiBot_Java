package modeles.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import modeles.CamCat;

import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;

public class FileIPCamReader {
	
	private FileIPCamReader() {
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<String> readCams() throws FileNotFoundException {
		
		ArrayList<String> cams = new ArrayList<String>();
		
		synchronized( CamCat.FILECAM ){
			InputStream ips = new FileInputStream( CamCat.FILECAM ); 
			InputStreamReader ipsr = new InputStreamReader( ips );
			BufferedReader br = new BufferedReader( ipsr );
			
			String ligne;
			try {
				while ((ligne=br.readLine())!=null){
					cams.add(ligne);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try { br.close(); } catch (IOException e) {}
			}
		}
		
		return cams;
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

}
