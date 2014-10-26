package vues.panels.controls;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import modeles.CtrlCat;

public class TourellePanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private JButton btTourDOWN;
	private JButton btTourLEFT;
	private JButton btTourRIGHT;
	private JButton btTourUP;

	private CtrlCat oModel;
	
	public TourellePanel( CtrlCat oModel ) {
		this.oModel = oModel;
		oModel.addObserver(this);
		
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Tourelle"));
		
		GridBagConstraints c = new GridBagConstraints();
		
		int iLigne = 0;
		btTourUP = new JButton("^");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btTourUP, c);
		
		btTourLEFT = new JButton("<");
		c.gridx = 0;
		c.gridy = iLigne;
		add(btTourLEFT, c);
		
		btTourRIGHT = new JButton(">");
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btTourRIGHT, c);
		
		btTourDOWN = new JButton("v");
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btTourDOWN, c);	
		
		if( oModel.isTourelleEnable() ){
			btTourDOWN.setEnabled(true);
			btTourLEFT.setEnabled(true);
			btTourRIGHT.setEnabled(true);
			btTourUP.setEnabled(true);
		}else{
			btTourDOWN.setEnabled(false);
			btTourLEFT.setEnabled(false);
			btTourRIGHT.setEnabled(false);
			btTourUP.setEnabled(false);
			
		}
			
		
	}

	@Override
	public void update(Observable model, Object arg1) {
		if( model == oModel ){
			if( oModel.isTourelleEnable() ){
				btTourDOWN.setEnabled(true);
				btTourLEFT.setEnabled(true);
				btTourRIGHT.setEnabled(true);
				btTourUP.setEnabled(true);
			}else{
				btTourDOWN.setEnabled(false);
				btTourLEFT.setEnabled(false);
				btTourRIGHT.setEnabled(false);
				btTourUP.setEnabled(false);
			}
		}
	}
	
	public void setListener( ActionListener ac){
		btTourDOWN.addActionListener( ac );
		btTourDOWN.setActionCommand("BTTOURDOWN");
		
		btTourLEFT.addActionListener( ac );
		btTourLEFT.setActionCommand("BTTOURLEFT");
		
		btTourRIGHT.addActionListener( ac );
		btTourRIGHT.setActionCommand("BTTOURRIGHT");
		
		btTourUP.addActionListener( ac );
		btTourUP.setActionCommand("BTTOURUP");
	}
}
