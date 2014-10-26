package vues.panels;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeles.CtrlCat;

public class DevicePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private JButton btnConnect;
	private JComboBox<String> combCamChoix;
	
	private CtrlCat oModel;
	
	
	public DevicePanel( CtrlCat oModel ) {
		this.oModel = oModel;
		oModel.addObserver(this);
		
		btnConnect = new JButton("Connect");	
		combCamChoix = new JComboBox<String>(this.oModel.getDevices());
		
		add( combCamChoix );
		add( btnConnect );
	}


	@Override
	public void update(Observable o, Object arg) {
		if( o == oModel ){
			
		}
		
	}
	
	public void setListener( ActionListener ac){		
		combCamChoix.addActionListener( ac );
		combCamChoix.setActionCommand("COMBCAMCHOIX");
		
		btnConnect.addActionListener( ac );
		btnConnect.setActionCommand("BTNCONNECT");
	}
	
	public String setSelectedDevice(){
		return (String)(combCamChoix.getSelectedItem());
	}
	
}
