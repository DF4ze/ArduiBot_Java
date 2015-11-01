package controleurs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Observable;
import java.util.Observer;

import controleurs.socketclient.com.Emission;
import modeles.catalogues.CtrlCat;
import modeles.catalogues.SocketCat;
import modeles.dao.communication.beansactions.SpeakAction;

public class ControleurVoix implements Observer{
	
	private CtrlCat oModel;
	private SocketCat socketCat;
	private Process proc;
	
	private static final String page = "yuri.php";
	
	private static FilenameFilter xmlFileFilter = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			return name.endsWith(".xml");
		}
	};
	

	public ControleurVoix( CtrlCat oModel, SocketCat socketCat ) {
		this.oModel = oModel;
		oModel.addObserver(this);
		
		this.socketCat = socketCat;
	}
	
	@Override
	public void update(Observable arg0, Object object) {
		if( object.equals("RECOVOCCHECK") ){
			if( oModel.isRecoVocCheck() ){
				if( Debug.isEnable() )
					System.out.println( "Lancement du programme de reconnaissance vocale" );
				
				prepareFiles();
				
				Runtime runtime = Runtime.getRuntime();
				try {
				//	runtime.exec(new String[] { "\"reco_voc\\WSRMacro.exe\" -d macros -d plugins -c 0.75" } );
				//	runtime.exec("reco_voc\\WSRMacro.exe -d macros -d plugins -c 0.75" );
				//	runtime.exec("WSRMacro.exe -d macros -d plugins -c 0.75", null, new File(".\\reco_voc\\") );
					
				//	runtime.exec(new String[] { ".\\reco_voc\\WSRMacro.exe", "-d",  "macros", "-d", "plugins", "-c", "0.75" } );
				//	runtime.exec(new String[] { "reco_voc\\voice.lnk"} );
				//	runtime.exec(new String[] { "reco_voc\\WSRMicro.cmd"} );
					proc = runtime.exec("WSRMacro.exe -d macros -d plugins -c 0.75" );

					Emission.addAction(new SpeakAction("Demarrage de la reconnaissance vocale"));
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}else{
				if( Debug.isEnable() )
					System.out.println( "Arret du programme de reconnaissance vocale" );
				proc.destroy();
				Emission.addAction(new SpeakAction("Arrai de la reconnaissance vocale"));
			}
		}
	}
	
	private void prepareFiles(){
		String ip = socketCat.getSelectedSocket().getIp();
		
		File repertoire = new File("macros");
		File[] files=repertoire.listFiles( xmlFileFilter );
		
		for( File file : files ){
			try{
				InputStream flux=new FileInputStream(file); 
				InputStreamReader lecture=new InputStreamReader(flux);
				BufferedReader buff=new BufferedReader(lecture);
				
				String ligne;
				String txtFile = "";
				while ((ligne=buff.readLine())!=null){
					txtFile += ligne+"\n";
				}
				buff.close(); 
				
				
				String updated = txtFile.replaceAll("uri=\"http.*"+page, "uri=\"http://"+ip+"/"+page ); 

				
				OutputStream os = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(osw);
				
				bw.write(updated);
				
				bw.close();
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
		}
	}

}
