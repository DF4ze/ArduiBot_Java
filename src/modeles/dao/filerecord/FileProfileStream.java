package modeles.dao.filerecord;

import java.io.IOException;

import modeles.entites.Profile;

public class FileProfileStream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public static void profileToJSON( java.util.List<Profile> profiles, String outFileName ) throws IOException {
		/*		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
	
		for( Profile pro : profiles ){
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
		*/
	}
}
