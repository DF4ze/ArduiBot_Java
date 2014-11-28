package vues.campanels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSlider;

import modeles.graphical.CtrlCat;
import modeles.graphical.PilotCat;
import vues.campanels.controls.DirectionPanel;
import vues.campanels.controls.ExtraPanel;
import vues.campanels.controls.TourellePanel;
import vues.tools.JIconSwitchButton;


public class ControlPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private DirectionPanel directionPanel;
	private TourellePanel tourPanel;
	private ExtraPanel extraPanel;
	private JIconSwitchButton reduceBtn;
	
	private CtrlCat oModel;
	
	
	public ControlPanel( CtrlCat oModel, PilotCat oModGraph ) {
		this.oModel = oModel;
		this.oModel.addObserver(this);
		
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createRaisedBevelBorder());
		
		GridBagConstraints c = new GridBagConstraints();

		ImageIcon iiRight = new ImageIcon("images/rightArrow.png");
		ImageIcon iiLeft = new ImageIcon("images/leftArrow.png");
		reduceBtn = new JIconSwitchButton(iiRight,iiRight,iiRight,iiLeft,iiLeft,iiLeft);
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 0);
		add(reduceBtn, c);
		
		// Panel Direction
		directionPanel = new DirectionPanel(oModel, oModGraph);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(directionPanel, c);
		
		// Panel Tourelle
		tourPanel = new TourellePanel(oModel, oModGraph );
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(tourPanel, c);
		
		// Panel d'Extra
		extraPanel = new ExtraPanel(oModel);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(extraPanel, c);
		
	}

	public void setListener( ActionListener ac){
		extraPanel.setListener( ac );
		reduceBtn.addActionListener(ac);
		reduceBtn.setActionCommandUP("REDUCECTRL");
		reduceBtn.setActionCommandDOWN("REDUCECTRL");
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

	@Override
	public void update(Observable arg0, Object message) {
		if( message.equals( "REDUCECTRL" ) ){
			if( oModel.isReduceCtrl() ){
				directionPanel.setVisible(true);
				tourPanel.setVisible(true);
				extraPanel.setVisible(true);
			}else{
				directionPanel.setVisible(false);
				tourPanel.setVisible(false);
				extraPanel.setVisible(false);
			}
		}
		
	}


}
