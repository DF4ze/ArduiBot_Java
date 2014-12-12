package modeles.dao.filerecord;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import modeles.catalogues.SocketCat;
import modeles.entites.SocketModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controleurs.Debug;

public class FileSocketStream {
	
	private FileSocketStream() {
	}
	
	public static void socketCatToJSON( SocketCat socksCat, String outFileName ) throws IOException {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		ArrayList<SocketModel> sockets = socksCat.getSockets();
		for( SocketModel sock : sockets ){
			HashMap<String, String> hmSocks = new HashMap<String, String>();
			hmSocks.put("name", sock.getName());
			hmSocks.put("ip", sock.getIp());
			hmSocks.put("port", Integer.toString(sock.getPort()));
			
			list.add( hmSocks );
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
	
	public static ArrayList<SocketModel> jsonToSocketCat( String inFileName ) throws  IOException, ParseException  {
		JSONParser jsp = new JSONParser();
		JSONArray jsa = (JSONArray) jsp.parse(new FileReader(inFileName));
		
		//ArrayList<HashMap<String, String>> alSocks = new ArrayList<HashMap<String, String>>();
		ArrayList<SocketModel> alSocks = new ArrayList<SocketModel>();
		for( Object o : jsa ){
			//HashMap<String, String> hmCam = new HashMap<String, String>();
			JSONObject jsCam = (JSONObject)o;
			
			String name = (String) jsCam.get("name");
			String ip = (String) jsCam.get("ip");
			String port = (String) jsCam.get("port");
			if( Debug.isEnable() )
				System.out.println("name : "+name+" ip : "+ip+" port : "+port);
			
			SocketModel sockTemp = new SocketModel();
			sockTemp.setName( (String) jsCam.get("name"));
			sockTemp.setPort( Integer.valueOf((String) jsCam.get("port")));
			sockTemp.setIp((String) jsCam.get("ip"));
			
			alSocks.add(sockTemp);
		}
		
		return alSocks;
	}
}
