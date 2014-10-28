package vues.campanels;

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
	private JButton btnAddCam;
	private JButton btnSaveCams;
	private JComboBox<String> combCamChoix;
	
	private CamCat oModCam;
	
	
	public DevicePanel( CamCat oModCam ) {
		this.oModCam = oModCam;
//		oModCam.addObserver(this);
		
		btnConnect = new JButton("Connect");	
		btnAddCam = new JButton("Add Cam");	
		btnSaveCams = new JButton("Save Cams");	
		combCamChoix = new JComboBox<String>(this.oModCam.getArrayCams());
		
		add( combCamChoix );
		add( btnConnect );
		add( btnAddCam );
		add( btnSaveCams );
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
		
		btnAddCam.addActionListener( ac );
		btnAddCam.setActionCommand("BTNADDCAM");
		
		btnSaveCams.addActionListener( ac );
		btnSaveCams.setActionCommand("BTNSAVECAMS");
	}
	
	public String getSelectedCam(){
		return (String)(combCamChoix.getSelectedItem());
	}
	
}
