package vues;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import vues.camPanels.BigPanel;

public class CamFrame extends JFrame {


	private static final long serialVersionUID = 1L;


	public CamFrame(String arg0) throws HeadlessException {
		super(arg0);
		
		BigPanel bp = new BigPanel();
		add( bp);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
	}



}
