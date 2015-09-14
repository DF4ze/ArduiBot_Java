package vues.campanels.bottom;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import modeles.catalogues.CtrlCat;
import modeles.catalogues.SocketCat;

public class BottomPanel extends JPanel {


	private static final long serialVersionUID = 1L;

	
	private ConnectPanel conP;
	private DistancePanel distP;


	public BottomPanel( CtrlCat oModel, SocketCat oModSock ) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		distP = new DistancePanel(oModel);
		add(distP);
		 
		conP = new ConnectPanel(oModel, oModSock);
		add( conP );
	}

	
	public void setListener( ActionListener ac){
		conP.setListener( ac );
	}

	public void setKeyListener( KeyListener cpCtrlPil ){
		conP.setKeyListener( cpCtrlPil );
	}
	public int getSelectedSocket(){
		return conP.getSelectedSocket();
	}
	public void setDistances( HashMap<Integer, Integer> distances ){
		distP.setDistances(distances);
	}

}
