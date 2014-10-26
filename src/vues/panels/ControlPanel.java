package vues.panels;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import modeles.CtrlCat;
import vues.panels.controls.DirectionPanel;
import vues.panels.controls.ExtraPanel;
import vues.panels.controls.TourellePanel;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private DirectionPanel directionPanel;
	private TourellePanel tourPanel;
	private ExtraPanel extraPanel;
	
	public ControlPanel( CtrlCat oModel ) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		directionPanel = new DirectionPanel(oModel);
		add(directionPanel);
		
		tourPanel = new TourellePanel(oModel);
		add(tourPanel);
		
		extraPanel = new ExtraPanel(oModel);
		add(extraPanel);
		
	}

	public void setListener( ActionListener ac){
		directionPanel.setListener( ac );
		tourPanel.setListener( ac );
		extraPanel.setListener( ac );
	}
}
