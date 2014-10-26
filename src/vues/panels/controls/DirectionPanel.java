package vues.panels.controls;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DirectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public DirectionPanel() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Direction"));
		
		GridBagConstraints c = new GridBagConstraints();
		
		int iLigne = 0;
		JButton btDirUP = new JButton("^");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btDirUP, c);
		
		JButton btDirLEFT = new JButton("<");
		c.gridx = 0;
		c.gridy = iLigne;
		add(btDirLEFT, c);
		
		JButton btDirRIGHT = new JButton(">");
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btDirRIGHT, c);
		
		JButton btDirDOWN = new JButton("v");
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btDirDOWN, c);	
		
	}

}
