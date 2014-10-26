package vues;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import exceptions.CamException;
import modeles.CtrlCat;
import vues.panels.BigPanel;

public class CamFrame extends JFrame {


	private static final long serialVersionUID = 1L;

	
	private BigPanel bp;

	public CamFrame(String arg0, CtrlCat oModel) throws HeadlessException {
		super(arg0);
		
		
		bp = new BigPanel( oModel );
		add( bp);		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	public void setListener( ActionListener ac){
		bp.setListener( ac );
	}
	
	public String setSelectedDevice(){
		return bp.setSelectedDevice();
	}
	
	public void setCamError( String sError ){
		bp.setCamError( sError );
		validate();
		pack();
	}
	
	public void showCam() throws CamException{
		bp.showCam();
		validate();
		pack();
	}
	

}
