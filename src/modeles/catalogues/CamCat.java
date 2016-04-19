package modeles.catalogues;

import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;

import org.json.simple.parser.ParseException;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamCompositeDriver;
import com.github.sarxos.webcam.ds.buildin.WebcamDefaultDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import controleurs.Debug;
import exceptions.CamException;
import modeles.dao.filerecord.FileIPCamStream;
import modeles.entites.CamDimension;
import modeles.entites.CamMode;

public class CamCat extends Observable{
	
	private int indexSelectedDevice = 0;
	private CamDimension[] camDimensions = new CamDimension[]{
			new CamDimension(320, 240, "QVGA"),
			new CamDimension(640, 480, "VGA"), 
			new CamDimension(800, 600, "SVGA"),
			new CamDimension(1024, 768, "XGA")};
	private CamMode[] camModes = new CamMode[]{ 
			new CamMode(IpCamMode.PUSH, "PUSH" ),
			new CamMode(IpCamMode.PULL, "PULL" )};
	
	public static final String FILECAM = "configs/listIPCams.json";
	public static final String FILECAPPHOTO = "captures/photo.png";
	
    static {
        Webcam.setDriver(new WebcamCompositeDriver(new WebcamDefaultDriver(), new IpCamDriver()));
    }
    
    
    
	public CamCat(){

		try {
			readCams();
		} catch (IOException | ParseException | CamException e) {
			if( Debug.isEnable() ){
				System.err.println("Erreur de lecture des cams lors du chargement");
				//e.printStackTrace();
			}
		}
	}




	public String[] getArrayCams() {
		//On récupère le(s) cam(s) locale(s)
		Collection<Webcam> webCams = Webcam.getWebcams();
		
		//On récupère le(s) cam(s) IP(s)
		Collection<IpCamDevice> ipCams = IpCamDeviceRegistry.getIpCameras();
		
		// Préparation du tableau de retour
		String[] retour = new String[ webCams.size() ];
		int i=0;
		
		// On fait le tour des webcams
		for( Webcam webCam : webCams ){
			String fullName = webCam.getName();
			boolean bFinded = false;
			// est-ce qu'on retrouve ce nom dans une camIP?
			for( IpCamDevice cam : ipCams ){
				// si oui, on affiche l'IP
				if( fullName.equals(cam.getName()) ){
					bFinded = true;
					fullName += " ("+cam.getURL()+")";
					break;
				}
			}
			// si non on affiche LOCALE
			if( !bFinded )
				fullName += " (locale)";
			
			retour[i++] = fullName;
		}
		
		if( Debug.isEnable() )
			System.out.println("Nb Cams : "+retour.length);
		
		return retour;
	}

	
	public void setSelectedCam(int selectedCam) {
		indexSelectedDevice = selectedCam;
	}

	public int getIndexSelectedDevice(){
		return indexSelectedDevice;
	}
	
	
	public void saveCams() throws IOException{

		FileIPCamStream.ipCamsToJSON(IpCamDeviceRegistry.getIpCameras(), CamCat.FILECAM);
	}
	
	public void readCams() throws IOException, ParseException, CamException{
		
		ArrayList<HashMap<String, String>> cams = FileIPCamStream.jsonToIpCams(CamCat.FILECAM);
		
		for( HashMap<String, String> cam : cams ){
			addCam(cam);
		}
	}
	
	public void addCam( HashMap<String, String> alDatas ) throws CamException, MalformedURLException{
		// formatage des Datas
		String name = null;
		String url = null;
		IpCamMode mode = null;
		Dimension size = null;
		
		Iterator<String> keySetIterator = alDatas.keySet().iterator();

		while(keySetIterator.hasNext()){
		  String key = keySetIterator.next();

		  if( key.equals("name") ){
			  if( alDatas.get(key).equals("") )
				  throw new CamException( "Le nom de la caméra ne peut pas être vide" );
			  else
				  name = alDatas.get(key);
			  
		  }else if( key.equals("url") ){
			  if( alDatas.get(key).equals("") )
				  throw new CamException( "L'URL de la caméra ne peut pas être vide" );
			  else
				  url = alDatas.get(key);
			  
		  }else if( key.equals("mode") ){
			  for( CamMode unMode : camModes ){
				  if( alDatas.get(key).equals(unMode) )
					  mode = unMode.getMode();
			  }
			  
		  }else if( key.equals("size") ){
			  for( CamDimension cdDim : camDimensions ){
				  if( alDatas.get(key).equals(cdDim) ){
					  size = cdDim;
					  break;
				  }
			  }
		  }else
			  ;
		}
		
		// vérifications...
		if( name == null )
			  throw new CamException( "Le nom de la caméra ne peut pas être vide" );
			
		if( url == null)
			  throw new CamException( "L'URL de la caméra ne peut pas être vide" );
			
		if( mode == null )
			mode = IpCamMode.PUSH;
		
		
		// on tente la création d'un IPDEVICE
		// création du device
		IpCamDevice myIpCam = new IpCamDevice( name, url, mode );
		
		// Application de la résolution
		if( size != null )
			myIpCam.setResolution(size);
		
		// les ajouter au registre ... peut planter si la cam existe deja
		try{
			IpCamDeviceRegistry.register( myIpCam );
		}catch(Exception e){}
		
		// On prévient les observateurs
		setChanged();
		notifyObservers("CAMADDED");

	}


	public void delCam( int indexCam ){
		IpCamDeviceRegistry.unregister( camIndexToIpCam(indexCam) );
		
		// On prévient les observateurs
		setChanged();
		notifyObservers("CAMDELETED");
	}

	protected IpCamDevice camIndexToIpCam( int indexCam ){
		return IpCamDeviceRegistry.getIpCameras().get( camIndexToIpCamIndex(indexCam));
	}
	protected int camIndexToIpCamIndex( int camIndex ){
		Webcam cam = Webcam.getWebcams().get(getIndexSelectedDevice());
		
		int index = -1;
		for( int i = 0; i< IpCamDeviceRegistry.getIpCameras().size(); i++){
			if( IpCamDeviceRegistry.getIpCameras().get(i).getName().equals(cam.getName()) ){
				index = i;
				break;
			}
		}
		
		return index;
	}

	public CamDimension[] getCamDimensions() {
		return camDimensions;
	}
	protected void setCamDimensions(CamDimension[] camDimensions) {
		this.camDimensions = camDimensions;
	}


	public CamMode[] getCamModes() {
		return camModes;
	}
	protected void setCamModes(CamMode[] camModes) {
		this.camModes = camModes;
	}

	public void takePicture() throws IOException{
		FileIPCamStream.recPicture( Webcam.getWebcams().get(indexSelectedDevice).getImage(), FILECAPPHOTO );
	}
	
	public void takeVideo(){
		
	}
}









