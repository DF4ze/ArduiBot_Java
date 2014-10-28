package vues.campanels.controls;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controleurs.Debug;
import modeles.CtrlCat;

public class DirectionPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private JButton btDirDOWN;
	private JButton btDirLEFT;
	private JButton btDirRIGHT;
	private JButton btDirUP;

	private CtrlCat oModel;

	public DirectionPanel( CtrlCat oModel ) {
		this.oModel = oModel;
		oModel.addObserver(this);
		
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Direction"));
		
		GridBagConstraints c = new GridBagConstraints();
		
		int iLigne = 0;
		btDirUP = new JButton("^");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btDirUP, c);
		
		btDirLEFT = new JButton("<");
		c.gridx = 0;
		c.gridy = iLigne;
		add(btDirLEFT, c);
		
		btDirRIGHT = new JButton(">");
		c.gridx = 2;
		c.gridy = iLigne++;
		add(btDirRIGHT, c);
		
		btDirDOWN = new JButton("v");
		c.gridx = 1;
		c.gridy = iLigne++;
		add(btDirDOWN, c);	
		
		if( oModel.isDirectionEnable() ){
			btDirDOWN.setEnabled(true);
			btDirLEFT.setEnabled(true);
			btDirRIGHT.setEnabled(true);
			btDirUP.setEnabled(true);
		}else{
			btDirDOWN.setEnabled(false);
			btDirLEFT.setEnabled(false);
			btDirRIGHT.setEnabled(false);
			btDirUP.setEnabled(false);
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
			}else{
				btDirDOWN.setEnabled(false);
				btDirLEFT.setEnabled(false);
				btDirRIGHT.setEnabled(false);
				btDirUP.setEnabled(false);
			}
	
		}
	}
	
	public void setListener( ActionListener ac){
		btDirDOWN.addActionListener( ac );
		btDirDOWN.setActionCommand("BTDIRDOWN");
		
		btDirLEFT.addActionListener( ac );
		btDirLEFT.setActionCommand("BTDIRLEFT");
		
		btDirRIGHT.addActionListener( ac );
		btDirRIGHT.setActionCommand("BTDIRRIGHT");
		
		btDirUP.addActionListener( ac );
		btDirUP.setActionCommand("BTDIRUP");
		
		if( Debug.isEnable() )
			System.out.println("Listener Set");
	}
}
