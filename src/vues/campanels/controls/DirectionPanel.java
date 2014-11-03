package vues.campanels.controls;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;

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
	
	private JMapButton btDirDOWNLEFT;
	private JMapButton btDirDOWNRIGHT;
	private JMapButton btDirUPRIGHT;
	private JMapButton btDirUPLEFT;
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
		btDirUPLEFT = new JMapButton();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = iLigne;
		add(btDirUPLEFT, c);
		
		btDirUP = new JMapButton();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = iLigne;
		add(btDirUP, c);
		
		btDirUPRIGHT = new JMapButton();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btDirUPRIGHT, c);
		
		
		
		btDirLEFT = new JMapButton();
		c.gridx = 0;
		c.gridy = iLigne;
		add(btDirLEFT, c);
		
		btDirCENTER = new JMapButton();
		c.gridx = 1;
		c.gridy = iLigne;
		add(btDirCENTER, c);
		
		btDirRIGHT = new JMapButton();
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btDirRIGHT, c);
		
		
		
		btDirDOWNLEFT = new JMapButton();
		c.gridx = 0;
		c.gridy = iLigne;
		add(btDirDOWNLEFT, c);	
		
		btDirDOWN = new JMapButton();
		c.gridx = 1;
		c.gridy = iLigne;
		add(btDirDOWN, c);	
		
		btDirDOWNRIGHT = new JMapButton();
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btDirDOWNRIGHT, c);	
		
		if( oModel.isDirectionEnable() ){
			for(Entry<String, JButton> entry : getDirectionBtn().entrySet()  ){
				entry.getValue().setEnabled(true);
			}
			
		}else{
			for(Entry<String, JButton> entry : getDirectionBtn().entrySet()  ){
				entry.getValue().setEnabled(false);
			}
		}

	}

	@Override
	public void update(Observable o, Object message) {

		if( o == oModel && message.equals("DIRECTIONENABLE")){
			
			if( Debug.isEnable() )
				System.out.println("Direction Panel : Update Receved");
			
			if( oModel.isDirectionEnable() ){
				for(Entry<String, JButton> entry : getDirectionBtn().entrySet()  ){
					entry.getValue().setEnabled(true);
				}
				
			}else{
				for(Entry<String, JButton> entry : getDirectionBtn().entrySet()  ){
					entry.getValue().setEnabled(false);
				}
			}
			
		}else if( o == oModGraph && message.equals("BGDIRECTION")){
			bgp.setBG(oModGraph.getBgDir());
		}
	}
	
	public void setPilotListener( MouseListener cpCtrlPil ){

		for(Entry<String, JButton> entry : getDirectionBtn().entrySet()  ){
			entry.getValue().addMouseListener(cpCtrlPil);
		}

		btDirCENTER.addMouseMotionListener((MouseMotionListener)cpCtrlPil);
	}	
	
	public HashMap<String, JButton> getDirectionBtn(){
		HashMap<String, JButton> directionBtn = new HashMap<String, JButton>();
		directionBtn.put("up", btDirUP);
		directionBtn.put("down", btDirDOWN);
		directionBtn.put("right", btDirRIGHT);
		directionBtn.put("left", btDirLEFT);
		
		directionBtn.put("upleft", btDirUPLEFT);
		directionBtn.put("upright", btDirUPRIGHT);
		directionBtn.put("downright", btDirDOWNRIGHT);
		directionBtn.put("downleft", btDirDOWNLEFT);
		
		directionBtn.put("center", btDirCENTER);
		return directionBtn;
	}

}
