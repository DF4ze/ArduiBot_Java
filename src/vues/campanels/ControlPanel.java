package vues.campanels;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSlider;

import modeles.CtrlCat;
import modeles.GraphPilotCat;
import vues.campanels.controls.DirectionPanel;
import vues.campanels.controls.ExtraPanel;
import vues.campanels.controls.TourellePanel;
import vues.tools.JIconButton;


public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private DirectionPanel directionPanel;
	private TourellePanel tourPanel;
	private ExtraPanel extraPanel;
	private JIconButton reduceBtn;
	
	
	public ControlPanel( CtrlCat oModel, GraphPilotCat oModGraph ) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		reduceBtn = new JIconButton(new ImageIcon("images/rightArrow.png"), new ImageIcon("images/rightArrow.png"), new ImageIcon("images/rightArrow.png"));
		
		// Panel Direction
		directionPanel = new DirectionPanel(oModel, oModGraph);
		add(directionPanel);
		
		// Panel Tourelle
		tourPanel = new TourellePanel(oModel, oModGraph );
		add(tourPanel);
		
		// Panel d'Extra
		extraPanel = new ExtraPanel(oModel);
		add(extraPanel);
		
	}

	public void setListener( ActionListener ac){
		extraPanel.setListener( ac );
	}
	public void setPilotListener( MouseListener cpCtrlPil ){
		directionPanel.setPilotListener( cpCtrlPil );
		tourPanel.setPilotListener( cpCtrlPil );
	}
	public void setKeyListener( KeyListener cpCtrlPil ){
		directionPanel.setKeyListener( cpCtrlPil );
		tourPanel.setKeyListener( cpCtrlPil );
		this.addKeyListener(cpCtrlPil);
	}	
	public HashMap<String, Component> getDirectionBtn(){
		return directionPanel.getDirectionBtn();
	}
	public HashMap<String, Component> getTourelleBtn(){
		return tourPanel.getTourelleBtn();
	}
	
	public HashMap<String, JSlider> getDirectionSliders(){
		return directionPanel.getDirectionSliders();
	}
	public HashMap<String, JSlider> getTourelleSliders(){
		return tourPanel.getTourelleSliders();
	}

//	public void setDirBackGround( String bgName ){
//		directionPanel.setBG( bgName );
//	}
//	public void setTourBackGround( String bgName ){
//		tourelleBgPanel.setBG( bgName );
//	}
}
