package vues.panels;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class BigPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public BigPanel() {
		setLayout(new BorderLayout());
		
		ControlPanel ctrlP = new ControlPanel();
		add( ctrlP, BorderLayout.EAST );
		
		CamPanel camP = new CamPanel();
		add( camP, BorderLayout.CENTER );
		
		DevicePanel dp = new DevicePanel();
		add( dp, BorderLayout.NORTH );
		
	}


}
