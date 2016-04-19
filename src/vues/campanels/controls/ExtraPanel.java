package vues.campanels.controls;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import controleurs.Debug;
import modeles.catalogues.CtrlCat;
import vues.tools.JIconSwitchButton;

public class ExtraPanel extends JPanel implements Observer{


	private static final long serialVersionUID = 1L;
	
	private JCheckBox cbLight;
	private JCheckBox cbStrob;
	private JCheckBox cbLazer;
	private JCheckBox cbStandBy;
	private JIconSwitchButton btnConnect;
	
	private CtrlCat oModel;

	public ExtraPanel( CtrlCat oModel ) {
		this.oModel = oModel;
		oModel.addObserver(this);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder("Options"));
		
		cbLight = new JCheckBox("Light");
		cbStrob = new JCheckBox("Strob");
		cbLazer = new JCheckBox("Lazer");
		cbStandBy = new JCheckBox("StandBy");
		btnConnect = new JIconSwitchButton( new ImageIcon("images/play.png"), new ImageIcon("images/play_over.png"), new ImageIcon("images/play_down.png"), new ImageIcon("images/stop.png"), new ImageIcon("images/stop_over.png"), new ImageIcon("images/stop_down.png") );		

		add(cbLight);
		add(cbStrob);
		add(cbLazer);	
		add(cbStandBy);	
		add(btnConnect);	
		
		if( oModel.isExtraEnable() ){
			cbLight.setEnabled(true);
			cbStrob.setEnabled(true);
			cbLazer.setEnabled(true);
			cbStandBy.setEnabled(true);
			btnConnect.setEnabled(true);
		}else{
			cbLight.setEnabled(false);
			cbStrob.setEnabled(false);
			cbLazer.setEnabled(false);
			cbStandBy.setEnabled(false);
			btnConnect.setEnabled(false);
		}
			
		
	}

	@Override
	public void update(Observable o, Object message) {
		
		if( o == oModel && message.equals("EXTRAENABLE")){
			if( Debug.isEnable() )
				System.out.println("Extra Panel : Update Receved");

			cbLight.setSelected(oModel.isLightCheck());
			cbStrob.setSelected(oModel.isStrobCheck());
			cbLazer.setSelected(oModel.isLazerCheck());
			cbStandBy.setSelected(oModel.isStandByCheck());
			if( oModel.isExtraEnable() ){
				cbLight.setEnabled(true);
				cbStrob.setEnabled(true);
				cbLazer.setEnabled(true);
				cbStandBy.setEnabled(true);
				btnConnect.setEnabled(true);
			}else{
				cbLight.setEnabled(false);
				cbStrob.setEnabled(false);
				cbLazer.setEnabled(false);
				cbStandBy.setEnabled(false);
				btnConnect.setEnabled(false);
			}		
		}else if( message.equals("LIGHTCHECK") ){
			cbLight.setSelected(oModel.isLightCheck());
			
		}else if( message.equals("STROBCHECK") ){
			cbStrob.setSelected(oModel.isStrobCheck());
			
		}else if( message.equals("LAZERCHECK") ){
			cbLazer.setSelected(oModel.isLazerCheck());
			
		}if( message.equals("STANDBYCHECK") ){
			cbStandBy.setSelected(oModel.isStandByCheck());
			
		}if( message.equals("WEBCAMSERVICE") ){
			;
		}
	}
	
	public void setListener( ActionListener ac){
		cbLight.addActionListener( ac );
		cbLight.setActionCommand("CBLIGHT");
		
		cbStrob.addActionListener( ac );
		cbStrob.setActionCommand("CBSTROB");
		
		cbLazer.addActionListener( ac );
		cbLazer.setActionCommand("CBLAZER");
		
		cbStandBy.addActionListener( ac );
		cbStandBy.setActionCommand("CBSTANDBY");

		btnConnect.addActionListener( ac );
		btnConnect.setActionCommandDOWN("BTNWEBCAMSERVICE");
		btnConnect.setActionCommandUP("BTNWEBCAMSERVICE");
	}
}
