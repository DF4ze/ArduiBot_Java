package vues.campanels;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import modeles.CamCat;
import controleurs.Debug;

public class DevicePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private JButton btnConnect;
	private JButton btnStop;
	private JButton btnAddCam;
	private JButton btnDelCam;
	private JButton btnSaveCams;
	private JButton btnReadCams;
	private JComboBox<String> combCamChoix;
	private DefaultComboBoxModel<String> combModelCamChoix;
	
	private CamCat oModCam;
	
	
	public DevicePanel( CamCat oModCam ) {
		this.oModCam = oModCam;
		oModCam.addObserver(this);
		
		btnConnect 			= new JButton("Connect");	
		btnStop 			= new JButton("Stop");	
		btnAddCam 			= new JButton("Add");	
		btnDelCam 			= new JButton("Del");	
		btnSaveCams 		= new JButton("Save");
		btnReadCams 		= new JButton("Read");
		combModelCamChoix 	= new DefaultComboBoxModel<String>(this.oModCam.getArrayCams());
		combCamChoix 		= new JComboBox<String>(combModelCamChoix);
		
		add( combCamChoix );
		add( btnConnect );
		add( btnStop );
		add( btnAddCam );
		add( btnDelCam );
		add( btnSaveCams );
		if( Debug.isEnable() )
			add( btnReadCams );
	}


	@Override
	public void update(Observable o, Object message) {
		if( o == oModCam ){
			if( message.equals("CAMADDED") || message.equals("CAMDELETED") ){
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
		btnConnect.setActionCommand("BTNCONNECTCAM");
		
		btnStop.addActionListener( ac );
		btnStop.setActionCommand("BTNSTOPCAM");
		
		btnAddCam.addActionListener( ac );
		btnAddCam.setActionCommand("BTNADDCAM");
		
		btnDelCam.addActionListener( ac );
		btnDelCam.setActionCommand("BTNDELCAM");
		
		btnSaveCams.addActionListener( ac );
		btnSaveCams.setActionCommand("BTNSAVECAMS");
		
		btnReadCams.addActionListener( ac );
		btnReadCams.setActionCommand("BTNREADCAMS");
	}
	
	public int getSelectedCam(){
		return combCamChoix.getSelectedIndex();
	}
	
}
