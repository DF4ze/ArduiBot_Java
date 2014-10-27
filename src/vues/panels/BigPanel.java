package vues.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleurs.Debug;
import exceptions.CamException;
import modeles.CtrlCat;

public class BigPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ControlPanel ctrlP;
	private CamPanel camP;
	private DevicePanel devP;
	private JLabel lblAccueil;
	
	private CtrlCat oModel;
	
	public BigPanel(CtrlCat oModel) {
		this.oModel = oModel;
		setLayout(new BorderLayout());
		
		ctrlP = new ControlPanel( oModel);
		add( ctrlP, BorderLayout.EAST );
		
		devP = new DevicePanel(oModel);
		add( devP, BorderLayout.NORTH );
		
		lblAccueil = new JLabel("Veuillez Sélectionner une caméra");
		add( lblAccueil, BorderLayout.CENTER );
		
	}

	public void setListener( ActionListener ac){
		ctrlP.setListener( ac );
		//camP.setListener( ac );
		devP.setListener( ac );
	}
	
	public String getSelectedDevice(){
		return devP.getSelectedDevice();
	}
	
	public void showCam() throws CamException{
		try{ remove(lblAccueil); }catch( NullPointerException e ){}
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
		
	}
	
	public void setCamError( String sError ){
		try{ remove(camP); }catch( NullPointerException e ){}
		try{ remove(lblAccueil); }catch( NullPointerException e ){}		
		
		JLabel lblError = new JLabel( sError );
		add( lblError);
		
	}

}
