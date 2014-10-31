package modeles.dao;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import modeles.CamCat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;

import controleurs.Debug;

public class FileIPCamStream {
	
	private FileIPCamStream() {
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

	public static void ipCamToJSON( java.util.List<IpCamDevice> Devices, String outFileName ) throws IOException {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
	
		for( IpCamDevice dev : Devices ){
			HashMap<String, String> hmDev = new HashMap<String, String>();
			hmDev.put("name", dev.getName());
			hmDev.put("url", dev.getURL().toString());
			hmDev.put("mode", dev.getMode().toString());
//			hmDev.put("res_height", Integer.toString(dev.getResolution().height));
//			hmDev.put("res_width", Integer.toString(dev.getResolution().width));
			
			list.add( hmDev );
		}
		
		String jsonString = JSONValue.toJSONString(list);
		
		if( Debug.isEnable() )
			System.out.println( "JSON : "+jsonString );
		

		File outFile = new File( outFileName );
		if( !outFile.exists() ){
			outFile.createNewFile();
		}		
		OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(outFile)));
		osw.write(jsonString);
		osw.close();
	}
	
	public static ArrayList<HashMap<String, String>> JSONToIpCam( String inFileName ) throws  IOException, ParseException  {
		JSONParser jsp = new JSONParser();
		JSONArray jsa = (JSONArray) jsp.parse(new FileReader(inFileName));
		
		ArrayList<HashMap<String, String>> alCams = new ArrayList<HashMap<String, String>>();
		for( Object o : jsa ){
			HashMap<String, String> hmCam = new HashMap<String, String>();
			JSONObject jsCam = (JSONObject)o;
			
			String name = (String) jsCam.get("name");
			String mode = (String) jsCam.get("mode");
			String url = (String) jsCam.get("url");
			if( Debug.isEnable() )
				System.out.println("name : "+name+" url : "+url+" mode : "+mode);
			
			hmCam.put( "name", (String) jsCam.get("name"));
			hmCam.put( "mode", (String) jsCam.get("mode"));
			hmCam.put( "url", (String) jsCam.get("url"));
			
			alCams.add(hmCam);
		}
		
		return alCams;
	}
}
