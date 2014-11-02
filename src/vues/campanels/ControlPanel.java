package vues.campanels;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
		
		// Panel Direction
		JPanel emptyDirP = new JPanel();
		emptyDirP.setBorder(BorderFactory.createTitledBorder("Direction"));
		BgPanel directionBgPanel = new BgPanel( "images/EMaps-Center-Direction-icon.png" );
		emptyDirP.add(directionBgPanel);
		
		directionPanel = new DirectionPanel(oModel, directionBgPanel);
		directionBgPanel.add(directionPanel);
		
		add( emptyDirP );
		
		// Panel Tourelle
		JPanel emptyTourP = new JPanel();
		emptyTourP.setBorder(BorderFactory.createTitledBorder("Tourelle"));
		BgPanel tourelleBgPanel = new BgPanel( "images/EMaps-Center-Direction-icon.png" );
		emptyTourP.add(tourelleBgPanel);

		tourPanel = new TourellePanel(oModel, tourelleBgPanel);
		tourelleBgPanel.add(tourPanel);
		add(emptyTourP);
		
		extraPanel = new ExtraPanel(oModel);
		add(extraPanel);
		
	}

	public void setListener( ActionListener ac){
//		directionPanel.setListener( ac );
//		tourPanel.setListener( ac );
		extraPanel.setListener( ac );
	}
	public void setPilotListener( MouseListener cpCtrlPil ){
		directionPanel.setPilotListener( cpCtrlPil );
		tourPanel.setPilotListener( cpCtrlPil );
	}
	public HashMap<String, JButton> getDirectionBtn(){
		return directionPanel.getDirectionBtn();
	}
	public HashMap<String, JButton> getTourelleBtn(){
		return tourPanel.getTourelleBtn();
		
	}
}
