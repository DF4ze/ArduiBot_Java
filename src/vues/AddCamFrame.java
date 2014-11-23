package vues;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;

import modeles.graphical.CamCat;
import vues.addcampanels.BigPanel;

public class AddCamFrame extends JFrame {


	private static final long serialVersionUID = 1L;

	private BigPanel bp;
	
	public AddCamFrame(String title, CamCat oModCam) throws HeadlessException {
		super(title);
		
		bp = new BigPanel( oModCam );
		add( bp);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setListener( ActionListener ac ){
		bp.setListener(ac);
	}

	public HashMap<String, String> getValues(){
		return bp.getValues();
	}
}
