package vues.campanels;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import modeles.CtrlCat;
import vues.campanels.controls.DirectionPanel;
import vues.campanels.controls.ExtraPanel;
import vues.campanels.controls.TourellePanel;
import vues.tools.BgPanel;


public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private DirectionPanel directionPanel;
	private TourellePanel tourPanel;
	private ExtraPanel extraPanel;
	
	public ControlPanel( CtrlCat oModel ) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel emptyP = new JPanel();
		emptyP.setBorder(BorderFactory.createTitledBorder("Direction"));
		BgPanel directionBgPanel = new BgPanel( "images/EMaps-Center-Direction-icon.png" );
		emptyP.add(directionBgPanel);
		
		directionPanel = new DirectionPanel(oModel, directionBgPanel);
		directionBgPanel.add(directionPanel);
		
		add( emptyP );
		
		tourPanel = new TourellePanel(oModel);
		add(tourPanel);
		
		extraPanel = new ExtraPanel(oModel);
		add(extraPanel);
		
	}

	public void setListener( ActionListener ac){
		directionPanel.setListener( ac );
		tourPanel.setListener( ac );
		extraPanel.setListener( ac );
	}
}
