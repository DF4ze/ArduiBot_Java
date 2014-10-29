package vues.campanels;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import controleurs.Debug;
import exceptions.CamException;
import modeles.CamCat;
import modeles.CtrlCat;

public class BigPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ControlPanel ctrlP;
	private WebcamPanel camP;
	private DevicePanel devP;
	private JLabel lblAccueil;
	
//	private CtrlCat oModel;
	private CamCat oModCam;
	
	public BigPanel(CtrlCat oModel, CamCat oModCam) {
//		this.oModel = oModel;
		this.oModCam = oModCam;
		
		setLayout(new BorderLayout());
		
		ctrlP = new ControlPanel( oModel);
		add( ctrlP, BorderLayout.EAST );
		
		devP = new DevicePanel(oModCam);
		add( devP, BorderLayout.NORTH );
		
		lblAccueil = new JLabel("Veuillez Sélectionner une caméra");
		add( lblAccueil, BorderLayout.CENTER );
		
	}

	public void setListener( ActionListener ac){
		ctrlP.setListener( ac );
		//camP.setListener( ac );
		devP.setListener( ac );
	}
	
	public int getSelectedCam(){
		return devP.getSelectedCam();
	}
	
	public void showCam() throws CamException{
		// on tente de retirer le label d'accueil
		try{ remove(lblAccueil); }catch( NullPointerException e ){}
		
		// on tente de retirer le WebcamPanel qui y aurait deja
		try{
			remove(camP);
			if( Debug.isEnable() )
				System.out.println("***OldCam Removed");
			
		}catch( NullPointerException e ){}
		
		//on ajoute la nouvelle cam
		camP = new WebcamPanel(Webcam.getWebcams().get(oModCam.getIndexSelectedDevice()));
		camP.setFillArea(true);
		add( camP, BorderLayout.CENTER );
	}
	
/*	public void showCamBis() throws CamException{
		
		try{
			oModel.deleteObserver(camP);
			remove(camP);
			if( Debug.isEnable() )
				System.out.println("***Cam Removed");
			
			camP.resetCam();
			if( Debug.isEnable() )
				System.out.println("***Cam reset");
		}catch( NullPointerException e ){}
		
		camP = new CamPanel(oModel);
		if( Debug.isEnable() )
			System.out.println("***Cam New");
		
		add( camP, BorderLayout.CENTER );
		
	}*/
	
	public void setCamError( String sError ){
		try{ remove(camP); }catch( NullPointerException e ){}
		try{ remove(lblAccueil); }catch( NullPointerException e ){}		
		
		JLabel lblError = new JLabel( sError );
		add( lblError);
		
	}

}
