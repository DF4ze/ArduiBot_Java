package vues.campanels.controls;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import modeles.CtrlCat;
import vues.tools.BgPanel;
import vues.tools.JMapButton;
import controleurs.Debug;

public class TourellePanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private JMapButton btTourDOWN;
	private JMapButton btTourLEFT;
	private JMapButton btTourRIGHT;
	private JMapButton btTourUP;
	private BgPanel bgp;
	
	private CtrlCat oModel;
	
	public TourellePanel( CtrlCat oModel, BgPanel bgp ) {
		this.bgp = bgp;
		this.oModel = oModel;
		oModel.addObserver(this);
		
		setLayout(new GridBagLayout());
		//setBorder(BorderFactory.createTitledBorder("Tourelle"));
		setOpaque(false);
		
		GridBagConstraints c = new GridBagConstraints();
		
		int iLigne = 0;
		btTourUP = new JMapButton(bgp, "images/Maps-North-Direction-icon.png", "images/EDirection-North-icon.png");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btTourUP, c);
		
		btTourLEFT = new JMapButton(bgp, "images/Maps-West-Direction-icon.png", "images/EDirection-West-icon.png");
		c.gridx = 0;
		c.gridy = iLigne;
		add(btTourLEFT, c);
		
		btTourRIGHT = new JMapButton(bgp, "images/Maps-East-Direction-icon.png", "images/EDirection-East-icon.png");
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btTourRIGHT, c);
		
		btTourDOWN = new JMapButton(bgp, "images/Maps-South-Direction-icon.png", "images/EDirection-South-icon.png");
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btTourDOWN, c);	
		
		if( oModel.isTourelleEnable() ){
			btTourDOWN.setEnabled(true);
			btTourLEFT.setEnabled(true);
			btTourRIGHT.setEnabled(true);
			btTourUP.setEnabled(true);
			bgp.setBG("images/EMaps-Center-Direction-icon.png");
		}else{
			btTourDOWN.setEnabled(false);
			btTourLEFT.setEnabled(false);
			btTourRIGHT.setEnabled(false);
			btTourUP.setEnabled(false);
			bgp.setBG("images/EMaps-Center-Direction-icon_disabled.png");			
		}
			
		
	}

	@Override
	public void update(Observable o, Object message) {

		if( o == oModel && message.equals("TOURELLEENABLE")){
			if( Debug.isEnable() )
				System.out.println("Tourelle Panel : Update Receved");

			if( oModel.isTourelleEnable() ){
				btTourDOWN.setEnabled(true);
				btTourLEFT.setEnabled(true);
				btTourRIGHT.setEnabled(true);
				btTourUP.setEnabled(true);
				bgp.setBG("images/EMaps-Center-Direction-icon.png");
			}else{
				btTourDOWN.setEnabled(false);
				btTourLEFT.setEnabled(false);
				btTourRIGHT.setEnabled(false);
				btTourUP.setEnabled(false);
				bgp.setBG("images/EMaps-Center-Direction-icon_disabled.png");
			}
		}
	}
	

	public void setPilotListener( MouseListener cpCtrlPil ){
		btTourDOWN.addMouseListener(cpCtrlPil);
		btTourUP.addMouseListener(cpCtrlPil);
		btTourLEFT.addMouseListener(cpCtrlPil);
		btTourRIGHT.addMouseListener(cpCtrlPil);
	}
	public HashMap<String, JButton> getTourelleBtn(){
		HashMap<String, JButton> tourBtn = new HashMap<String, JButton>();
		tourBtn.put("up", btTourUP);
		tourBtn.put("down", btTourDOWN);
		tourBtn.put("right", btTourRIGHT);
		tourBtn.put("left", btTourLEFT);
		return tourBtn;
	}
}
