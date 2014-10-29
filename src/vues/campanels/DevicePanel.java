package vues.campanels;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controleurs.Debug;
import modeles.CamCat;

public class DevicePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private JButton btnConnect;
	private JButton btnAddCam;
	private JButton btnSaveCams;
	private JComboBox<String> combCamChoix;
	private DefaultComboBoxModel<String> combModelCamChoix;
	
	private CamCat oModCam;
	
	
	public DevicePanel( CamCat oModCam ) {
		this.oModCam = oModCam;
		oModCam.addObserver(this);
		
		btnConnect = new JButton("Connect");	
		btnAddCam = new JButton("Add Cam");	
		btnSaveCams = new JButton("Save Cams");
		combModelCamChoix = new DefaultComboBoxModel<String>(this.oModCam.getArrayCams());
		combCamChoix = new JComboBox<String>(combModelCamChoix);
		
		add( combCamChoix );
		add( btnConnect );
		add( btnAddCam );
		add( btnSaveCams );
	}


	@Override
	public void update(Observable o, Object message) {
		if( o == oModCam ){
			if( message.equals("CAMADDED") ){
				if( Debug.isEnable() )
					System.out.println( "DevicePanel : CamAdded" );
				combModelCamChoix = new DefaultComboBoxModel<String>(this.oModCam.getArrayCams());
				combCamChoix.setModel(combModelCamChoix);
			}
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
	
	public int getSelectedCam(){
		return combCamChoix.getSelectedIndex();
	}
	
}
