package vues.campanels;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import modeles.CamCat;
import modeles.CtrlCat;
import vues.tools.JIconButton;
import vues.tools.JIconSwitchButton;
import controleurs.Debug;

public class DevicePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

//	private JIconButton btnConnect;
//	private JIconButton btnStop;
	private JIconSwitchButton btnConnect;
	private JIconButton btnAddCam;
	private JIconButton btnDelCam;
	private JIconButton btnSaveCams;
	private JIconButton btnReadCams;
	private JIconButton btnTakePicture;
	private JIconButton btnTakeVideo;
	private JComboBox<String> combCamChoix;
	private DefaultComboBoxModel<String> combModelCamChoix;
	
	private CamCat oModCam;
	private CtrlCat oModCtrl;
	
	public DevicePanel( CtrlCat oModCtrl, CamCat oModCam ) {
		this.oModCam = oModCam;
		oModCam.addObserver(this);
		this.oModCtrl = oModCtrl;
		oModCtrl.addObserver(this);
		
//		btnConnect 		= new JIconButton( new ImageIcon("images/play.png"), new ImageIcon("images/play_over.png"), new ImageIcon("images/play_down.png") );
//		btnStop 		= new JIconButton( new ImageIcon("images/stop.png"), new ImageIcon("images/stop_over.png"), new ImageIcon("images/stop_down.png") );	
		
		btnConnect 		= new JIconSwitchButton( new ImageIcon("images/play.png"), new ImageIcon("images/play_over.png"), new ImageIcon("images/play_down.png"), new ImageIcon("images/stop.png"), new ImageIcon("images/stop_over.png"), new ImageIcon("images/stop_down.png") );		
		btnAddCam 		= new JIconButton( new ImageIcon("images/add.png"), new ImageIcon("images/add_over.png"), new ImageIcon("images/add_down.png") );	
		btnDelCam 		= new JIconButton( new ImageIcon("images/del.png"), new ImageIcon("images/del_over.png"), new ImageIcon("images/del_down.png") );	
		btnSaveCams 	= new JIconButton( new ImageIcon("images/save.png"), new ImageIcon("images/save_over.png"), new ImageIcon("images/save_down.png") );
		btnReadCams 	= new JIconButton( new ImageIcon("images/open.png"),new ImageIcon("images/open_over.png"), new ImageIcon("images/open_down.png") );
		btnTakePicture 	= new JIconButton( new ImageIcon("images/photo.png"),new ImageIcon("images/photo_over.png"), new ImageIcon("images/photo_down.png") );
		btnTakeVideo 	= new JIconButton( new ImageIcon("images/video.png"),new ImageIcon("images/video_over.png"), new ImageIcon("images/video_down.png") );
		
		combModelCamChoix 	= new DefaultComboBoxModel<String>(this.oModCam.getArrayCams());
		combCamChoix 		= new JComboBox<String>(combModelCamChoix);
		
		add( combCamChoix );
		add( btnConnect );
		//add( btnStop );
		add( btnAddCam );
		add( btnDelCam );
		add( btnSaveCams );
		if( Debug.isEnable() )
			add( btnReadCams );
		add( btnTakePicture );
		add( btnTakeVideo );
//		add( new JButton("Lina-Joy") );
//		add( new JButton("Flora") );
//		add( new JButton("papa") );
//		add( new JButton("maman") );
		
		
		if( oModCtrl.isTakePictureEnable() ){
			btnTakePicture.setEnabled(true);
		}else{
			btnTakePicture.setEnabled(false);	
		}
		
		if( oModCtrl.isTakeVideoEnable() ){
			btnTakeVideo.setEnabled(true);
		}else{
			btnTakeVideo.setEnabled(false);	
		}
		
		
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
			
		}else if( o == oModCtrl ){
			if( message.equals("TAKEPICTUREENABLE") ){
				btnTakePicture.setEnabled(oModCtrl.isTakePictureEnable());
			
			}else if( message.equals("TAKEVIDEOENABLE") ){
				btnTakeVideo.setEnabled(oModCtrl.isTakeVideoEnable());
			}
		}
		
		
	}
	
	public void setListener( ActionListener ac){		
		combCamChoix.addActionListener( ac );
		combCamChoix.setActionCommand("SELECTEDDEVICE");
		
		btnConnect.addActionListener( ac );
		btnConnect.setActionCommandUP("BTNCONNECTCAM");
		btnConnect.setActionCommandDOWN("BTNSTOPCAM");
		
//		btnStop.addActionListener( ac );
//		btnStop.setActionCommand("BTNSTOPCAM");
		
		btnAddCam.addActionListener( ac );
		btnAddCam.setActionCommand("BTNADDCAM");
		
		btnDelCam.addActionListener( ac );
		btnDelCam.setActionCommand("BTNDELCAM");
		
		btnSaveCams.addActionListener( ac );
		btnSaveCams.setActionCommand("BTNSAVECAMS");
		
		btnReadCams.addActionListener( ac );
		btnReadCams.setActionCommand("BTNREADCAMS");
		
		btnTakePicture.addActionListener( ac );
		btnTakePicture.setActionCommand("BTNTAKEPICTURE");
		
		btnTakeVideo.addActionListener( ac );
		btnTakeVideo.setActionCommand("BTNTAKEVIDEO");
	}
	
	public int getSelectedCam(){
		return combCamChoix.getSelectedIndex();
	}
	
}
