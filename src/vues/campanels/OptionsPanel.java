package vues.campanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import modeles.catalogues.CtrlCat;
import vues.campanels.options.DistantSoundPanel;
import vues.campanels.options.TTSPanel;
import vues.campanels.options.VoicePanel;
import vues.tools.JIconSwitchButton;

public class OptionsPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private JIconSwitchButton reduceBtn;
	private VoicePanel voicePanel;
	private TTSPanel ttsPanel;
	private DistantSoundPanel dsPanel;
	
	private CtrlCat oModel;
	
	
	public OptionsPanel( CtrlCat oModel ) {
		this.oModel = oModel;
		this.oModel.addObserver(this);

		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createRaisedBevelBorder());
		
		GridBagConstraints c = new GridBagConstraints();

		ImageIcon iiRight = new ImageIcon("images/rightArrow.png");
		ImageIcon iiLeft = new ImageIcon("images/leftArrow.png");
		reduceBtn = new JIconSwitchButton(iiLeft,iiLeft,iiLeft,iiRight,iiRight,iiRight);
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 3;
		c.gridwidth = 1;
		c.insets = new Insets(0, 0, 0, 0);
		add(reduceBtn, c);
		
		voicePanel = new VoicePanel(oModel);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(voicePanel, c);
		
		ttsPanel = new TTSPanel( oModel );
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(ttsPanel, c);
		
		dsPanel = new DistantSoundPanel( oModel );
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(dsPanel, c);
		

		
		
	}

	public void setListener( ActionListener ac){
		voicePanel.setListener( ac );
		ttsPanel.setListener(ac);
		dsPanel.setListener(ac);
		
		reduceBtn.addActionListener(ac);
		reduceBtn.setActionCommandUP("REDUCEOPTS");
		reduceBtn.setActionCommandDOWN("REDUCEOPTS");
	}
	
	@Override
	public void update(Observable arg0, Object message) {
		
		if( message.equals( "REDUCEOPTS" ) ){
			if( oModel.isReduceOpts() ){
				voicePanel.setVisible(true);
				ttsPanel.setVisible(true);
				dsPanel.setVisible(true);
				
			}else{
				voicePanel.setVisible(false);
				ttsPanel.setVisible(false);
				dsPanel.setVisible(false);
			}
		}	
	}

	public String getTextToSay(){
		return ttsPanel.getTextToSay();
	}

}
