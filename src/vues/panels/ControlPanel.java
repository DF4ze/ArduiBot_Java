package vues.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import vues.panels.controls.DirectionPanel;
import vues.panels.controls.ExtraPanel;
import vues.panels.controls.TourellePanel;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ControlPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		DirectionPanel directionPanel = new DirectionPanel();
		add(directionPanel);
		
		TourellePanel tourPanel = new TourellePanel();
		add(tourPanel);
		
		ExtraPanel extraPanel = new ExtraPanel();
		add(extraPanel);
		
	}

}
