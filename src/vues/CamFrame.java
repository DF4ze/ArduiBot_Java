package vues;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import modeles.CamCat;
import modeles.CtrlCat;
import vues.campanels.BigPanel;
import exceptions.CamException;

public class CamFrame extends JFrame {


	private static final long serialVersionUID = 1L;

	
	private BigPanel bp;

	public CamFrame(String arg0, CtrlCat oModel, CamCat oModCam) throws HeadlessException {
		super(arg0);
		
		
		bp = new BigPanel( oModel, oModCam );
		add( bp);		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	public void setListener( ActionListener ac){
		bp.setListener( ac );
	}
	public void setPilotListener(MouseListener cpCtrlPil){
		bp.setPilotListener( cpCtrlPil );
	}
	
	public HashMap<String, JButton> getDirectionBtn(){
		return bp.getDirectionBtn();
	}
	public HashMap<String, JButton> getTourelleBtn(){
		return bp.getTourelleBtn();
		
	}
	
	public int getSelectedCam(){
		return bp.getSelectedCam();
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
	
	public void stopCam(){
		bp.stopCam();
	}
}
