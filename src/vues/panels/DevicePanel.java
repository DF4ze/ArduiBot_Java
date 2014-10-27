package vues.panels;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import modeles.CamCat;

public class DevicePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private JButton btnConnect;
	private JComboBox<String> combCamChoix;
	
	private CamCat oModCam;
	
	
	public DevicePanel( CamCat oModCam ) {
		this.oModCam = oModCam;
		oModCam.addObserver(this);
		
		btnConnect = new JButton("Connect");	
		combCamChoix = new JComboBox<String>(this.oModCam.getArrayCams());
		
		add( combCamChoix );
		add( btnConnect );
	}


	@Override
	public void update(Observable o, Object arg) {
		if( o == oModCam ){
			
		}
		
	}
	
	public void setListener( ActionListener ac){		
		combCamChoix.addActionListener( ac );
		combCamChoix.setActionCommand("SELECTEDDEVICE");
		
		btnConnect.addActionListener( ac );
		btnConnect.setActionCommand("BTNCONNECT");
	}
	
	public String getSelectedCam(){
		return (String)(combCamChoix.getSelectedItem());
	}
	
}
