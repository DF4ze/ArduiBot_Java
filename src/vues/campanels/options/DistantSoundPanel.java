package vues.campanels.options;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import modeles.catalogues.CtrlCat;

public class DistantSoundPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private JButton plus;
	private JButton minus;
	private JButton mute;
	
	private CtrlCat oModel;
	
	public DistantSoundPanel( CtrlCat oModel ) {
		this.oModel = oModel;
		oModel.addObserver(this);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createTitledBorder("Son distant"));

		plus = new JButton("+");
		minus = new JButton("-");
		mute = new JButton("M");
		
		add( plus );
		add( minus );
		add( mute );
		
		majBoxes();
	}

	private void majBoxes(){
			
		plus.setEnabled(oModel.isTtsEnable());
		minus.setEnabled(oModel.isTtsEnable());
		mute.setEnabled(oModel.isTtsEnable());
	}
	
	public void setListener( ActionListener ac){
		plus.addActionListener(ac);
		plus.setActionCommand( "DISTANTSOUNDPLUS" );
		minus.addActionListener(ac);
		minus.setActionCommand( "DISTANTSOUNDMINUS" );
		mute.addActionListener(ac);
		mute.setActionCommand( "DISTANTSOUNDMUTE" );
		
	}

	@Override
	public void update(Observable o, Object message) {
		if( message.equals("DISTANTSOUNDENABLE") ){
			majBoxes();
		
		}
		
	}
}
