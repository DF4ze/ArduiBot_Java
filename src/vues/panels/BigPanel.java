package vues.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import modeles.CtrlCat;

public class BigPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ControlPanel ctrlP;
	private CamPanel camP;
	private DevicePanel devP;
	
	private CtrlCat oModel;
	
	public BigPanel(CtrlCat oModel) {
		this.oModel = oModel;
		setLayout(new BorderLayout());
		
		ctrlP = new ControlPanel( oModel);
		add( ctrlP, BorderLayout.EAST );
		
		devP = new DevicePanel(oModel);
		add( devP, BorderLayout.NORTH );	
	}

	public void setListener( ActionListener ac){
		ctrlP.setListener( ac );
		//camP.setListener( ac );
		devP.setListener( ac );
	}
	
	public String setSelectedDevice(){
		return devP.setSelectedDevice();
	}
	
	public void showCam(){
		camP = new CamPanel(oModel);
		add( camP, BorderLayout.CENTER );
	}
}
