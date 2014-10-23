package vues.camPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ControlPanel() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		JButton btUP = new JButton("^");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		add(btUP, c);
		
		JButton btLEFT = new JButton("<");
		c.gridx = 0;
		c.gridy = 1;
		add(btLEFT, c);
		
		JButton btRIGHT = new JButton(">");
		c.gridx = 2;
		c.gridy = 1;
		add(btRIGHT, c);
		
		JButton btDOWN = new JButton("v");
		c.gridx = 1;
		c.gridy = 2;
		add(btDOWN, c);
		
		
	}

}
