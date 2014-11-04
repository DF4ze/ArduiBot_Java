package vues.campanels;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import modeles.CamCat;
import modeles.CtrlCat;
import modeles.GraphPilotCat;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import controleurs.Debug;
import exceptions.CamException;

public class BigPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ControlPanel ctrlP;
	private WebcamPanel camP;
	private DevicePanel devP;
	private JLabel lblAccueil;
	
//	private CtrlCat oModel;
	private CamCat oModCam;
	
	public BigPanel(CtrlCat oModel, CamCat oModCam, GraphPilotCat oModGraph) {
//		this.oModel = oModel;
		this.oModCam = oModCam;
		
		setLayout(new BorderLayout());
		
		ctrlP = new ControlPanel( oModel, oModGraph);
		add( ctrlP, BorderLayout.EAST );
		
		devP = new DevicePanel(oModel, oModCam);
		add( devP, BorderLayout.NORTH );
		
		lblAccueil = new JLabel("Veuillez S�lectionner une cam�ra");
		add( lblAccueil, BorderLayout.CENTER );
		
	}

	public void setListener( ActionListener ac){
		ctrlP.setListener( ac );
		devP.setListener( ac );
	}
	public void setPilotListener( MouseListener cpCtrlPil ){
		ctrlP.setPilotListener( cpCtrlPil );
	}
	public void setKeyListener( KeyListener cpCtrlPil ){
		ctrlP.setKeyListener( cpCtrlPil );
		devP.setKeyListener( cpCtrlPil );
		this.addKeyListener(cpCtrlPil);
	}

	public HashMap<String, JButton> getDirectionBtn(){
		return ctrlP.getDirectionBtn();
	}
	public HashMap<String, JButton> getTourelleBtn(){
		return ctrlP.getTourelleBtn();
		
	}

	public HashMap<String, JSlider> getDirectionSliders(){
		return ctrlP.getDirectionSliders();
	}
	public HashMap<String, JSlider> getTourelleSliders(){
		return ctrlP.getTourelleSliders();
		
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
	
	public void stopCam(){
		camP.stop();
	}
	
	public void setCamError( String sError ){
		try{ remove(camP); }catch( NullPointerException e ){}
		try{ remove(lblAccueil); }catch( NullPointerException e ){}		
		
		JLabel lblError = new JLabel( sError );
		add( lblError);
		
	}
	
//	public void setDirBackGround( String bgName ){
//		ctrlP.setDirBackGround( bgName );
//	}
//	public void setTourBackGround( String bgName ){
//		ctrlP.setTourBackGround( bgName );
//	}

}
