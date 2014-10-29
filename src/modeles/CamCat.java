package modeles;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;

import modeles.tools.CamDimension;
import modeles.tools.CamMode;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamCompositeDriver;
import com.github.sarxos.webcam.ds.buildin.WebcamDefaultDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import controleurs.Debug;
import exceptions.CamException;

public class CamCat extends Observable{
	
//	private ArrayList<String> cams = new  ArrayList<String>();
//	private ArrayList<IpCamDevice> devices = new ArrayList<IpCamDevice>();
	private int indexSelectedDevice = 0;
	private CamDimension[] camDimensions = new CamDimension[]{
			new CamDimension(320, 240, "QVGA"),
			new CamDimension(640, 480, "VGA"), 
			new CamDimension(800, 600, "SVGA"),
			new CamDimension(1024, 768, "XGA")};
	private CamMode[] camModes = new CamMode[]{ 
			new CamMode(IpCamMode.PUSH, "PUSH" ),
			new CamMode(IpCamMode.PULL, "PULL" )};
	
	public static final String FILECAM = "ipcams/listIPCams.properties";
	
    static {
        Webcam.setDriver(new WebcamCompositeDriver(new WebcamDefaultDriver(), new IpCamDriver()));
    }
    
    
    
	public CamCat(){
/*		// savoir s'il y a la cam locale de connect�
		if( Webcam.getDefault() != null ) {
			cams.add("local");
		} 
		
		// R�cup�rer les IP des cam distantes
		try {
			this.cams.addAll( FileIPCamReader.readCams() );
		
			// les transformer en DEVICE
			for( String url : cams ){
				if( url.equals("local") )
					devices.add(null);
				else{
					// on tente la cr�ation d'un IPDEVICE
					try {
						IpCamDevice myIpCam = new IpCamDevice( url, url, IpCamMode.PUSH);
						devices.add( myIpCam );
						// les ajouter au registre
						IpCamDeviceRegistry.register( myIpCam );
					} catch (MalformedURLException e) {
						if( Debug.isEnable() )
							e.printStackTrace();
					}
	
				}
			}
		} catch (FileNotFoundException e1) {
			if( Debug.isEnable() )
				e1.printStackTrace();
		}

*/		
	}



//	public ArrayList<String> getCams() {
//		return cams;
//	}
	public String[] getArrayCams() {
		//On r�cup�re le(s) cam(s) locale(s)
		Collection<Webcam> webCams = Webcam.getWebcams();
		
		//On r�cup�re le(s) cam(s) IP(s)
		Collection<IpCamDevice> ipCams = IpCamDeviceRegistry.getIpCameras();
		
		// Pr�paration du tableau de retour
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

	
//	public ArrayList<IpCamDevice> getDevices() {
//		return devices;
//	}
	



//	public String getSelectedCam() {
//		return selectedCam;
//	}
	public void setSelectedCam(int selectedCam) {
		indexSelectedDevice = selectedCam;
	}

	public int getIndexSelectedDevice(){
		return indexSelectedDevice;
	}
	
	
//	public void saveCams(){
//		FileIPCamReader.writeIPCamToProperties(devices);
//	}
	
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
				  throw new CamException( "Le nom de la cam�ra ne peut pas �tre vide" );
			  else
				  name = alDatas.get(key);
			  
		  }else if( key.equals("url") ){
			  if( alDatas.get(key).equals("") )
				  throw new CamException( "L'URL de la cam�ra ne peut pas �tre vide" );
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
		
		// v�rifications...
		if( name == null )
			  throw new CamException( "Le nom de la cam�ra ne peut pas �tre vide" );
			
		if( url == null)
			  throw new CamException( "L'URL de la cam�ra ne peut pas �tre vide" );
			
		if( mode == null )
			mode = IpCamMode.PUSH;
		
		
		// on tente la cr�ation d'un IPDEVICE
		// cr�ation du device
		IpCamDevice myIpCam = new IpCamDevice( name, url, mode );
		
		// Application de la r�solution
		if( size != null )
			myIpCam.setResolution(size);
		
		// les ajouter au registre
		IpCamDeviceRegistry.register( myIpCam );
		
		// ajout a la liste
		//devices.add( myIpCam );
			
		// On pr�vient les observateurs
		setChanged();
		notifyObservers("CAMADDED");

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

}









