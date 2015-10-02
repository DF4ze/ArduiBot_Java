package vues.campanels.options;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import modeles.catalogues.CtrlCat;

public class VoicePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private JCheckBox cbActivReco;
	private JCheckBox cbLocalSound;
	private JCheckBox cbDistantSound;
	
	private CtrlCat oModel;

	public VoicePanel( CtrlCat oModel ) {
		this.oModel = oModel;
		oModel.addObserver(this);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder("Reco. Vocale"));

		cbActivReco = new JCheckBox("Reco. activée");
		cbLocalSound = new JCheckBox("Son local");
		cbDistantSound = new JCheckBox("Son distant");
		
		add( cbActivReco );
		add( cbLocalSound );
		add( cbDistantSound );
		
		majCheckBoxes();
	}
	
	public void setListener( ActionListener ac){
		cbActivReco.addActionListener(ac);
		cbActivReco.setActionCommand( "CBACTIVRECO" );
		cbLocalSound.addActionListener(ac);
		cbLocalSound.setActionCommand( "CBLOCALSOUND" );
		cbDistantSound.addActionListener(ac);
		cbDistantSound.setActionCommand( "CBDISTANTSOUND" );
		
	}

	@Override
	public void update(Observable o, Object message) {
		if( message.equals("RECOVOCCHECK") || message.equals("SONDISTANTCHECK") || message.equals("SONLOCALCHECK") || message.equals("RECOVOCENABLE") ){
			majCheckBoxes();
		
		}
		
	}
	
	private void majCheckBoxes(){
		cbActivReco.setSelected(oModel.isRecoVocCheck());
		cbLocalSound.setSelected(oModel.isSonLocalCheck());
		cbDistantSound.setSelected(oModel.isSonDistantCheck());
		
		cbActivReco.setEnabled(oModel.isRecoVocEnable());
		cbLocalSound.setEnabled(oModel.isRecoVocEnable());
		cbDistantSound.setEnabled(oModel.isRecoVocEnable());
	}
}
