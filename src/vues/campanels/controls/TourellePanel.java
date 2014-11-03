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

public class TourellePanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private JMapButton btTourDOWN;
	private JMapButton btTourLEFT;
	private JMapButton btTourCENTER;
	private JMapButton btTourRIGHT;
	private JMapButton btTourUP;
	
	private JMapButton btTourDOWNLEFT;
	private JMapButton btTourUPLEFT;
	private JMapButton btTourDOWNRIGHT;
	private JMapButton btTourUPRIGHT;
	private BgPanel bgp;
	
	private CtrlCat oModel;
	private GraphPilotCat oModGraph;
	
	public TourellePanel( CtrlCat oModel, GraphPilotCat oModGraph, BgPanel bgp ) {
		this.bgp = bgp;
		this.oModel = oModel;
		this.oModGraph = oModGraph;
		oModel.addObserver(this);
		oModGraph.addObserver(this);
		
		setLayout(new GridBagLayout());
		//setBorder(BorderFactory.createTitledBorder("Tourelle"));
		setOpaque(false);
		
		GridBagConstraints c = new GridBagConstraints();
		
		int iLigne = 0;
		btTourUPLEFT = new JMapButton();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = iLigne;
		add(btTourUPLEFT, c);
		
		btTourUP = new JMapButton();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = iLigne;
		add(btTourUP, c);
		
		btTourUPRIGHT = new JMapButton();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btTourUPRIGHT, c);
		
		
		
		btTourLEFT = new JMapButton();
		c.gridx = 0;
		c.gridy = iLigne;
		add(btTourLEFT, c);
		
		btTourCENTER = new JMapButton();
		c.gridx = 1;
		c.gridy = iLigne;
		add(btTourCENTER, c);

		btTourRIGHT = new JMapButton();
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btTourRIGHT, c);
		
		
		btTourDOWNLEFT = new JMapButton();
		c.gridx = 0;
		c.gridy = iLigne;
		add(btTourDOWNLEFT, c);	
		
		btTourDOWN = new JMapButton();
		c.gridx = 1;
		c.gridy = iLigne;
		add(btTourDOWN, c);	
		
		btTourDOWNRIGHT = new JMapButton();
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btTourDOWNRIGHT, c);	
		
		if( oModel.isTourelleEnable() ){
			for(Entry<String, JButton> entry : getTourelleBtn().entrySet()  ){
				entry.getValue().setEnabled(true);
			}
		}else{
			for(Entry<String, JButton> entry : getTourelleBtn().entrySet()  ){
				entry.getValue().setEnabled(false);
			}
		}
			
		
	}

	@Override
	public void update(Observable o, Object message) {

		if( o == oModel && message.equals("TOURELLEENABLE")){
			if( Debug.isEnable() )
				System.out.println("Tourelle Panel : Update Receved");

			if( oModel.isTourelleEnable() ){
				for(Entry<String, JButton> entry : getTourelleBtn().entrySet()  ){
					entry.getValue().setEnabled(true);
				}
			}else{
				for(Entry<String, JButton> entry : getTourelleBtn().entrySet()  ){
					entry.getValue().setEnabled(false);
				}
			}
			
		}else if( o == oModGraph && message.equals("BGTOURELLE")){
			bgp.setBG(oModGraph.getBgTour());
		}
	}
	

	public void setPilotListener( MouseListener cpCtrlPil ){
		for(Entry<String, JButton> entry : getTourelleBtn().entrySet()  ){
			entry.getValue().addMouseListener(cpCtrlPil);
		}
		btTourCENTER.addMouseMotionListener((MouseMotionListener)cpCtrlPil);
	}
	
	public HashMap<String, JButton> getTourelleBtn(){
		HashMap<String, JButton> tourBtn = new HashMap<String, JButton>();
		tourBtn.put("up", btTourUP);
		tourBtn.put("down", btTourDOWN);
		tourBtn.put("right", btTourRIGHT);
		tourBtn.put("left", btTourLEFT);
		
		tourBtn.put("upleft", btTourUPLEFT);
		tourBtn.put("downright", btTourDOWNRIGHT);
		tourBtn.put("upright", btTourUPRIGHT);
		tourBtn.put("downleft", btTourDOWNLEFT);
		
		tourBtn.put("center", btTourCENTER);
		return tourBtn;
	}
}
