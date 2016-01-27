package vues.campanels.options;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import modeles.catalogues.CtrlCat;

public class StreamMicPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private JCheckBox CBStreamMic;
	
	private CtrlCat oModel;
	
	public StreamMicPanel( CtrlCat oModel ) {
		this.oModel = oModel;
		oModel.addObserver(this);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createTitledBorder("Micro"));

		CBStreamMic = new JCheckBox("écouter micro");
		
		add( CBStreamMic );
		
		
		majBoxes();
	}

	private void majBoxes(){
		CBStreamMic.setEnabled(oModel.isTtsEnable());
	}
	
	public void setListener( ActionListener ac){
		CBStreamMic.addActionListener(ac);
		CBStreamMic.setActionCommand( "CBSTREAMMIC" );		
	}

	@Override
	public void update(Observable o, Object message) {
		if( message.equals("STREAMMICENABLE") ){
			majBoxes();
		}
		
	}
}
