package vues;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import modeles.catalogues.SocketCat;
import modeles.entites.SocketModel;
import vues.addsockpanels.BigPanel;

public class AddSocketFrame extends JFrame {


	private static final long serialVersionUID = 1L;

	private BigPanel bp;
	
	public AddSocketFrame(String title, SocketCat oModSocket) throws HeadlessException {
		super(title);
		
		bp = new BigPanel( oModSocket );
		add( bp);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setListener( ActionListener ac ){
		bp.setListener(ac);
	}

	public SocketModel getValues(){
		return bp.getValues();
	}
}
