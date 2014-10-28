package vues;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import vues.addcampanels.BigPanel;

public class AddCamFrame extends JFrame {


	private static final long serialVersionUID = 1L;

	private BigPanel bp;
	
	public AddCamFrame(String title) throws HeadlessException {
		super(title);
		
		bp = new BigPanel( );
		add( bp);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public void setListener( ActionListener ac ){
		bp.setListener(ac);
	}

}
