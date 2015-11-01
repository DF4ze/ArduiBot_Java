package vues.campanels.options;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import modeles.catalogues.CtrlCat;

public class TTSPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JButton buttonSay;
	private JRadioButton rbTTSPico;
	private JRadioButton rbTTSEspeak;
	
	private CtrlCat oModel;

	public TTSPanel( CtrlCat oModel ) {
		this.oModel = oModel;
		oModel.addObserver(this);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder("TTS"));
		
		textField = new JTextField();
		buttonSay = new JButton("Dire");
		rbTTSPico = new JRadioButton("Pico 2 wave");
		rbTTSEspeak = new JRadioButton("Espeak");
		
		rbTTSPico.setSelected(true);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rbTTSEspeak);
		bg.add(rbTTSPico);
		
		
		add( textField );
		add( buttonSay );
		add( rbTTSPico );
		add( rbTTSEspeak );
		
		majBoxes();
	}
	
	public void setListener( ActionListener ac){
		buttonSay.addActionListener(ac);
		buttonSay.setActionCommand( "BUTTONSAY" );
		rbTTSEspeak.addActionListener(ac);
		rbTTSEspeak.setActionCommand( "RBESPEAK" );
		rbTTSPico.addActionListener(ac);
		rbTTSPico.setActionCommand( "RBPICO" );
		textField.addActionListener(ac);
		textField.setActionCommand( "BUTTONSAY" );
		
	}

	@Override
	public void update(Observable o, Object message) {
		if( message.equals("TTSENABLE") ){
			majBoxes();
		
		}
		
	}
	
	private void majBoxes(){
		if( oModel.getTtsSelected() == CtrlCat.ttsPico )
			rbTTSPico.setSelected(true);
		else if( oModel.getTtsSelected() == CtrlCat.ttsEspeak )
			rbTTSEspeak.setSelected(true);
			
		textField.setEnabled(oModel.isTtsEnable());
		buttonSay.setEnabled(oModel.isTtsEnable());
		rbTTSEspeak.setEnabled(oModel.isTtsEnable());
		rbTTSPico.setEnabled(oModel.isTtsEnable());
	}

	public String getTextToSay(){
		return textField.getText();
	}
}
