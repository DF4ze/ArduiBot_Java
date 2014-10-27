package modeles.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import modeles.CamCat;

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

}
