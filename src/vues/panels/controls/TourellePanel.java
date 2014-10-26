package vues.panels.controls;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TourellePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public TourellePanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Tourelle"));
		
		GridBagConstraints c = new GridBagConstraints();
		
		int iLigne = 0;
		JButton btTourUP = new JButton("^");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btTourUP, c);
		
		JButton btTourLEFT = new JButton("<");
		c.gridx = 0;
		c.gridy = iLigne;
		add(btTourLEFT, c);
		
		JButton btTourRIGHT = new JButton(">");
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btTourRIGHT, c);
		
		JButton btTourDOWN = new JButton("v");
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btTourDOWN, c);	
		
	}

}
