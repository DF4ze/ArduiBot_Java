package vues.campanels;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import modeles.CamCat;
import vues.tools.JIconButton;
import controleurs.Debug;

public class DevicePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private JIconButton btnConnect;
	private JIconButton btnStop;
	private JIconButton btnAddCam;
	private JIconButton btnDelCam;
	private JIconButton btnSaveCams;
	private JIconButton btnReadCams;
	private JComboBox<String> combCamChoix;
	private DefaultComboBoxModel<String> combModelCamChoix;
	
	private CamCat oModCam;
	
	
	public DevicePanel( CamCat oModCam ) {
		this.oModCam = oModCam;
		oModCam.addObserver(this);
		
		btnConnect 	= new JIconButton( new ImageIcon("images/play.png"), new ImageIcon("images/play_over.png"), new ImageIcon("images/play_down.png") );
		btnStop 	= new JIconButton( new ImageIcon("images/stop.png"), new ImageIcon("images/stop_over.png"), new ImageIcon("images/stop_down.png") );	
		btnAddCam 	= new JIconButton( new ImageIcon("images/add.png"), new ImageIcon("images/add_over.png"), new ImageIcon("images/add_down.png") );	
		btnDelCam 	= new JIconButton( new ImageIcon("images/del.png"), new ImageIcon("images/del_over.png"), new ImageIcon("images/del_down.png") );	
		btnSaveCams = new JIconButton( new ImageIcon("images/save.png"), new ImageIcon("images/save_over.png"), new ImageIcon("images/save_down.png") );
		btnReadCams = new JIconButton( new ImageIcon("images/open.png"),new ImageIcon("images/open_over.png"), new ImageIcon("images/open_down.png") );
		
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
