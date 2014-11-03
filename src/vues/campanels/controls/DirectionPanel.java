package vues.campanels.controls;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import modeles.CtrlCat;
import modeles.GraphPilotCat;
import vues.tools.BgPanel;
import vues.tools.JMapButton;
import controleurs.Debug;

public class DirectionPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private JMapButton btDirDOWN;
	private JMapButton btDirLEFT;
	private JMapButton btDirRIGHT;
	private JMapButton btDirUP;
	private JMapButton btDirCENTER;
	private BgPanel bgp;

	private CtrlCat oModel;
	private GraphPilotCat oModGraph;

	public DirectionPanel( CtrlCat oModel, GraphPilotCat oModGraph, BgPanel bgp ) {
		this.bgp = bgp;
		this.oModel = oModel;
		this.oModGraph = oModGraph;
		oModel.addObserver(this);
		oModGraph.addObserver(this);
		
		setLayout(new GridBagLayout());
		//setBorder(BorderFactory.createTitledBorder("Direction"));
		setOpaque(false);
		
		GridBagConstraints c = new GridBagConstraints();
		
		int iLigne = 0;
		btDirUP = new JMapButton(/*bgp, "images/Maps-North-Direction-icon.png", "images/EDirection-North-icon.png"*/);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btDirUP, c);
		
		btDirLEFT = new JMapButton(/*bgp, "images/Maps-West-Direction-icon.png", "images/EDirection-West-icon.png"*/);
		c.gridx = 0;
		c.gridy = iLigne;
		add(btDirLEFT, c);
		
		btDirCENTER = new JMapButton(/*bgp, "images/EMaps-Center-Direction-icon-clicked.png", "images/EMaps-Center-Direction-icon-over.png"*/);
		c.gridx = 1;
		c.gridy = iLigne;
		add(btDirCENTER, c);
		
		btDirRIGHT = new JMapButton(/*bgp, "images/Maps-East-Direction-icon.png", "images/EDirection-East-icon.png"*/);
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btDirRIGHT, c);
		
		btDirDOWN = new JMapButton(/*bgp, "images/Maps-South-Direction-icon.png", "images/EDirection-South-icon.png"*/);
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btDirDOWN, c);	
		
		if( oModel.isDirectionEnable() ){
			btDirDOWN.setEnabled(true);
			btDirLEFT.setEnabled(true);
			btDirRIGHT.setEnabled(true);
			btDirUP.setEnabled(true);
			btDirCENTER.setEnabled(true);
			/*bgp.setBG("images/EMaps-Center-Direction-icon.png");*/
		}else{
			btDirDOWN.setEnabled(false);
			btDirLEFT.setEnabled(false);
			btDirRIGHT.setEnabled(false);
			btDirUP.setEnabled(false);
			btDirCENTER.setEnabled(false);
			/*bgp.setBG("images/EMaps-Center-Direction-icon_disabled.png");*/
		}

	}

	@Override
	public void update(Observable o, Object message) {

		if( o == oModel && message.equals("DIRECTIONENABLE")){
			
			if( Debug.isEnable() )
				System.out.println("Direction Panel : Update Receved");
			
			if( oModel.isDirectionEnable() ){
				btDirDOWN.setEnabled(true);
				btDirLEFT.setEnabled(true);
				btDirRIGHT.setEnabled(true);
				btDirUP.setEnabled(true);
				btDirCENTER.setEnabled(true);
				/*bgp.setBG("images/EMaps-Center-Direction-icon.png");*/
			}else{
				btDirDOWN.setEnabled(false);
				btDirLEFT.setEnabled(false);
				btDirRIGHT.setEnabled(false);
				btDirUP.setEnabled(false);
				btDirCENTER.setEnabled(false);
				/*bgp.setBG("images/EMaps-Center-Direction-icon_disabled.png");*/
			}
			
		}else if( o == oModGraph && message.equals("BGDIRECTION")){
			bgp.setBG(oModGraph.getBgDir());
		}
	}
	
	public void setPilotListener( MouseListener cpCtrlPil ){

		btDirDOWN.addMouseListener(cpCtrlPil);
		btDirUP.addMouseListener(cpCtrlPil);
		btDirLEFT.addMouseListener(cpCtrlPil);
		btDirRIGHT.addMouseListener(cpCtrlPil);
		btDirCENTER.addMouseListener(cpCtrlPil);
		btDirCENTER.addMouseMotionListener((MouseMotionListener)cpCtrlPil);
	}	
	public HashMap<String, JButton> getDirectionBtn(){
		HashMap<String, JButton> directionBtn = new HashMap<String, JButton>();
		directionBtn.put("up", btDirUP);
		directionBtn.put("down", btDirDOWN);
		directionBtn.put("right", btDirRIGHT);
		directionBtn.put("left", btDirLEFT);
		directionBtn.put("center", btDirCENTER);
		return directionBtn;
	}

}
