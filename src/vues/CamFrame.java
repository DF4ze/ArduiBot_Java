package vues;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSlider;

import modeles.CamCat;
import modeles.CtrlCat;
import modeles.GraphPilotCat;
import vues.campanels.BigPanel;
import exceptions.CamException;

public class CamFrame extends JFrame {


	private static final long serialVersionUID = 1L;

	
	private BigPanel bp;

	public CamFrame(String arg0, CtrlCat oModel, CamCat oModCam, GraphPilotCat oModGraph) throws HeadlessException {
		super(arg0);
		
		
		bp = new BigPanel( oModel, oModCam, oModGraph );
		add( bp);		
		
		

		
		
		setIconImage((new ImageIcon("images/logo.png")).getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public void setListener( ActionListener ac){
		bp.setListener( ac );
	}
	public void setPilotListener(MouseListener cpCtrlPil){
		bp.setPilotListener( cpCtrlPil );
	}
	public void setKeyListener( KeyListener cpCtrlPil ){
		bp.setKeyListener( cpCtrlPil );
		this.addKeyListener(cpCtrlPil);
	}
	
	public HashMap<String, Component> getDirectionBtn(){
		return bp.getDirectionBtn();
	}
	public HashMap<String, Component> getTourelleBtn(){
		return bp.getTourelleBtn();
		
	}
	
	public HashMap<String, JSlider> getDirectionSliders(){
		return bp.getDirectionSliders();
	}
	public HashMap<String, JSlider> getTourelleSliders(){
		return bp.getTourelleSliders();
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
	
//	public void setDirBackGround( String bgName ){
//		bp.setDirBackGround( bgName );
//	}
//	public void setTourBackGround( String bgName ){
//		bp.setTourBackGround( bgName );
//	}
}
