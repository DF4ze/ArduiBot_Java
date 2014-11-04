package vues.campanels.controls;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

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
	
	private JCheckBox cbReverseY;

	private JSlider verticalSpeed;
	private JSlider horizontalSpeed;

	private CtrlCat oModel;
	private GraphPilotCat oModGraph;
	
	public TourellePanel( CtrlCat oModel, GraphPilotCat oModGraph ) {
		this.oModel = oModel;
		this.oModGraph = oModGraph;
		oModel.addObserver(this);
		oModGraph.addObserver(this);
		
		JPanel globalTourP = new JPanel();
		globalTourP.setBorder(BorderFactory.createTitledBorder("Tourelle"));
		globalTourP.setLayout(new BorderLayout());
		
		
		bgp = new BgPanel( "images/EMaps-Center-Direction-icon.png" );
		bgp.setLayout(new GridBagLayout());
		bgp.setOpaque(false);
		
		globalTourP.add(bgp, BorderLayout.CENTER );
		
		verticalSpeed = new JSlider(JSlider.VERTICAL, oModGraph.getMinVertSliderTourPos(), oModGraph.getMaxVertSliderTourPos(), oModGraph.getVertSliderTourPos());
		verticalSpeed.setPreferredSize(new Dimension(20, 50));
		globalTourP.add(verticalSpeed, BorderLayout.EAST);		

		JPanel southP = new JPanel();
		southP.setLayout(new BoxLayout(southP, BoxLayout.X_AXIS));

		horizontalSpeed = new JSlider(JSlider.HORIZONTAL, oModGraph.getMinHoriSliderTourPos(), oModGraph.getMaxHoriSliderTourPos(), oModGraph.getHoriSliderTourPos());
		horizontalSpeed.setPreferredSize(new Dimension(1, 20));
		southP.add( horizontalSpeed );

		cbReverseY = new JCheckBox();
		southP.add( cbReverseY );
		
		globalTourP.add(southP, BorderLayout.SOUTH);
		
		add( globalTourP );

		
		GridBagConstraints c = new GridBagConstraints();
		
		int iLigne = 0;
		btTourUPLEFT = new JMapButton();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = iLigne;
		bgp.add(btTourUPLEFT, c);
		
		btTourUP = new JMapButton();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = iLigne;
		bgp.add(btTourUP, c);
		
		btTourUPRIGHT = new JMapButton();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = iLigne++;
		bgp.add(btTourUPRIGHT, c);
		
		
		
		btTourLEFT = new JMapButton();
		c.gridx = 0;
		c.gridy = iLigne;
		bgp.add(btTourLEFT, c);
		
		btTourCENTER = new JMapButton();
		c.gridx = 1;
		c.gridy = iLigne;
		bgp.add(btTourCENTER, c);

		btTourRIGHT = new JMapButton();
		c.gridx = 2;
		c.gridy = iLigne++;
		bgp.add(btTourRIGHT, c);
		
		
		btTourDOWNLEFT = new JMapButton();
		c.gridx = 0;
		c.gridy = iLigne;
		bgp.add(btTourDOWNLEFT, c);	
		
		btTourDOWN = new JMapButton();
		c.gridx = 1;
		c.gridy = iLigne;
		bgp.add(btTourDOWN, c);	
		
		btTourDOWNRIGHT = new JMapButton();
		c.gridx = 2;
		c.gridy = iLigne++;
		bgp.add(btTourDOWNRIGHT, c);	
		
		if( oModel.isTourelleEnable() ){
			for(Entry<String, JButton> entry : getTourelleBtn().entrySet()  ){
				entry.getValue().setEnabled(true);
			}
			horizontalSpeed.setEnabled(true);
			verticalSpeed.setEnabled(true);
		}else{
			for(Entry<String, JButton> entry : getTourelleBtn().entrySet()  ){
				entry.getValue().setEnabled(false);
			}
			horizontalSpeed.setEnabled(false);
			verticalSpeed.setEnabled(false);
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
				horizontalSpeed.setEnabled(true);
				verticalSpeed.setEnabled(true);
			}else{
				for(Entry<String, JButton> entry : getTourelleBtn().entrySet()  ){
					entry.getValue().setEnabled(false);
				}
				horizontalSpeed.setEnabled(false);
				verticalSpeed.setEnabled(false);
			}
			
		}else if( o == oModGraph ){
			
			if(  message.equals("BGTOURELLE") )
				bgp.setBG(oModGraph.getBgTour());
			
			else if(message.equals("VERTITOURSLIDER")){
				verticalSpeed.setValue(oModGraph.getVertSliderTourPos());
				verticalSpeed.repaint();

			}else if(message.equals("HORITOURSLIDER")){
				horizontalSpeed.setValue(oModGraph.getHoriSliderTourPos());
				horizontalSpeed.repaint();

			}
		}
	}
	

	public void setPilotListener( MouseListener cpCtrlPil ){
		for(Entry<String, JButton> entry : getTourelleBtn().entrySet()  ){
			entry.getValue().addMouseListener(cpCtrlPil);
			entry.getValue().addMouseMotionListener((MouseMotionListener)cpCtrlPil);
		}
		verticalSpeed.addChangeListener((ChangeListener) cpCtrlPil);
		horizontalSpeed.addChangeListener((ChangeListener) cpCtrlPil);
	}
	public void setKeyListener( KeyListener cpCtrlPil ){
		for(Entry<String, JButton> entry : getTourelleBtn().entrySet()  ){
			entry.getValue().addKeyListener(cpCtrlPil);
		}
		horizontalSpeed.addKeyListener(cpCtrlPil);
		verticalSpeed.addKeyListener(cpCtrlPil);
		
		this.addKeyListener(cpCtrlPil);
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
	
	public HashMap<String, JSlider> getTourelleSliders(){
		HashMap<String, JSlider> tourSld = new HashMap<String, JSlider>();
		tourSld.put("verti", verticalSpeed);
		tourSld.put("hori", horizontalSpeed);
		return tourSld;
	}
}
