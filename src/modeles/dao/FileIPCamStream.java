package modeles.dao;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

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
	


	public static void recPicture( BufferedImage image, String fileNameTemplate ) throws IOException{
		File newPhotoTemplate = new File( fileNameTemplate );
		File newPhoto = new File( fileNameTemplate );
		int i=1;
		while( newPhoto.exists() ){
			String[] tName = newPhotoTemplate.getPath().split("\\.");
			
			String zeroFill = "";
			for( int j=1000 ; j > 1 ; j /= 10)
				if( i < j )
					zeroFill += "0";

			newPhoto =  new File( tName[0]+ zeroFill+ i +"."+tName[1]);
			i++;
		}
			
	//	if( image != null )	
			ImageIO.write(image, "PNG", newPhoto);
	}
	
	public static void ipCamsToJSON( java.util.List<IpCamDevice> Devices, String outFileName ) throws IOException {
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
	
	public static ArrayList<HashMap<String, String>> jsonToIpCams( String inFileName ) throws  IOException, ParseException  {
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
