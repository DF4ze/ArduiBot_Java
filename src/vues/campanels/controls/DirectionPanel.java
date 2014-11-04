package vues.campanels.controls;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

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
	
	private JCheckBox cbReverseY;
	
	private JSlider verticalSpeed;
	private JSlider horizontalSpeed;
	
	private BgPanel bgp;

	private CtrlCat oModel;
	private GraphPilotCat oModGraph;

	public DirectionPanel( CtrlCat oModel, GraphPilotCat oModGraph ) {
		this.oModel = oModel;
		this.oModGraph = oModGraph;
		oModel.addObserver(this);
		oModGraph.addObserver(this);
		
		
		JPanel globalDirP = new JPanel();
		globalDirP.setBorder(BorderFactory.createTitledBorder("Direction"));
		globalDirP.setLayout(new BorderLayout());
		
		
		bgp = new BgPanel( "images/EMaps-Center-Direction-icon.png" );
		bgp.setLayout(new GridBagLayout());
		bgp.setOpaque(false);
		
		globalDirP.add(bgp, BorderLayout.CENTER );
		
		verticalSpeed = new JSlider(JSlider.VERTICAL, oModGraph.getMinVertSliderDirPos(), oModGraph.getMaxVertSliderDirPos(), oModGraph.getVertSliderDirPos());
		verticalSpeed.setPreferredSize(new Dimension(20, 50));
		globalDirP.add(verticalSpeed, BorderLayout.EAST);		

		JPanel southP = new JPanel();
		southP.setLayout(new BoxLayout(southP, BoxLayout.X_AXIS));
		horizontalSpeed = new JSlider(JSlider.HORIZONTAL, oModGraph.getMinHoriSliderDirPos(), oModGraph.getMaxHoriSliderDirPos(), oModGraph.getHoriSliderDirPos());
		horizontalSpeed.setPreferredSize(new Dimension(1, 20));
		southP.add(horizontalSpeed);
		
		cbReverseY = new JCheckBox();
		southP.add(cbReverseY);
		
		globalDirP.add(southP, BorderLayout.SOUTH);
		
		
		add( globalDirP);
		
			
		
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		int iLigne = 0;
		btDirUPLEFT = new JMapButton();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = iLigne;
		bgp.add(btDirUPLEFT, c);
		
		btDirUP = new JMapButton();
		c.gridx = 1;
		c.gridy = iLigne;
		bgp.add(btDirUP, c);
		
		btDirUPRIGHT = new JMapButton();
		c.gridx = 2;
		c.gridy = iLigne++;
		bgp.add(btDirUPRIGHT, c);

		
		
		btDirLEFT = new JMapButton();
		c.gridx = 0;
		c.gridy = iLigne;
		c.gridheight = 1;
		bgp.add(btDirLEFT, c);
		
		btDirCENTER = new JMapButton();
		c.gridx = 1;
		c.gridy = iLigne;
		bgp.add(btDirCENTER, c);
		
		btDirRIGHT = new JMapButton();
		c.gridx = 2;
		c.gridy = iLigne++;
		bgp.add(btDirRIGHT, c);
		
		
		
		btDirDOWNLEFT = new JMapButton();
		c.gridx = 0;
		c.gridy = iLigne;
		bgp.add(btDirDOWNLEFT, c);	
		
		btDirDOWN = new JMapButton();
		c.gridx = 1;
		c.gridy = iLigne;
		bgp.add(btDirDOWN, c);	
		
		btDirDOWNRIGHT = new JMapButton();
		c.gridx = 2;
		c.gridy = iLigne++;
		bgp.add(btDirDOWNRIGHT, c);	

		

		
		if( oModel.isDirectionEnable() ){
			for(Entry<String, JButton> entry : getDirectionBtn().entrySet()  ){
				entry.getValue().setEnabled(true);
			}
			horizontalSpeed.setEnabled(true);
			verticalSpeed.setEnabled(true);
			
		}else{
			for(Entry<String, JButton> entry : getDirectionBtn().entrySet()  ){
				entry.getValue().setEnabled(false);
			}
			horizontalSpeed.setEnabled(false);
			verticalSpeed.setEnabled(false);
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
				
				horizontalSpeed.setEnabled(true);
				verticalSpeed.setEnabled(true);
			}else{
				for(Entry<String, JButton> entry : getDirectionBtn().entrySet()  ){
					entry.getValue().setEnabled(false);
				}
				horizontalSpeed.setEnabled(false);
				verticalSpeed.setEnabled(false);
			}
			
		}else if( o == oModGraph ){
			
			if( message.equals("BGDIRECTION"))
					bgp.setBG(oModGraph.getBgDir());
			
			else if(message.equals("VERTIDIRSLIDER")){				
				verticalSpeed.setValue(oModGraph.getVertSliderDirPos());
				verticalSpeed.repaint();
				
			}else if(message.equals("HORIDIRSLIDER")){
				horizontalSpeed.setValue(oModGraph.getHoriSliderDirPos());
				horizontalSpeed.repaint();
			}
		}
	}
	
	public void setPilotListener( MouseListener cpCtrlPil ){

		for(Entry<String, JButton> entry : getDirectionBtn().entrySet()  ){
			entry.getValue().addMouseListener(cpCtrlPil);
			entry.getValue().addMouseMotionListener((MouseMotionListener)cpCtrlPil);
		}

		verticalSpeed.addChangeListener((ChangeListener) cpCtrlPil);
		horizontalSpeed.addChangeListener((ChangeListener) cpCtrlPil);
	}	
	public void setKeyListener( KeyListener cpCtrlPil ){
		for(Entry<String, JButton> entry : getDirectionBtn().entrySet()  ){
			entry.getValue().addKeyListener(cpCtrlPil);
		}
		horizontalSpeed.addKeyListener(cpCtrlPil);
		verticalSpeed.addKeyListener(cpCtrlPil);
		
		this.addKeyListener(cpCtrlPil);
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
	
	public HashMap<String, JSlider> getDirectionSliders(){
		HashMap<String, JSlider> dirSld = new HashMap<String, JSlider>();
		dirSld.put("verti", verticalSpeed);
		dirSld.put("hori", horizontalSpeed);
		return dirSld;
	}


}
