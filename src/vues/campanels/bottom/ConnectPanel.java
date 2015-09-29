package vues.campanels.bottom;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modeles.catalogues.CtrlCat;
import modeles.catalogues.SocketCat;
import vues.tools.JIconButton;
import vues.tools.JIconSwitchButton;
import controleurs.Debug;

public class ConnectPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private JIconSwitchButton btnConnect;
	private JIconButton btnAddSock;
	private JIconButton btnDelSock;
	private JIconButton btnSaveSock;
	private JIconButton btnReadSock;
	private JComboBox<String> combSockChoix;
	private DefaultComboBoxModel<String> combModelSockChoix;
	// temporaire...
	private JLabel lblVoltage = new JLabel("0v");
	
	private SocketCat oModSock;
	private CtrlCat oModCtrl;
	
	public ConnectPanel( CtrlCat oModCtrl, SocketCat oModSock ) {
		this.oModSock = oModSock;
		oModSock.addObserver(this);
		this.oModCtrl = oModCtrl;
		oModCtrl.addObserver(this);

		setBorder(BorderFactory.createRaisedBevelBorder());

		
		btnConnect 		= new JIconSwitchButton( new ImageIcon("images/play.png"), new ImageIcon("images/play_over.png"), new ImageIcon("images/play_down.png"), new ImageIcon("images/stop.png"), new ImageIcon("images/stop_over.png"), new ImageIcon("images/stop_down.png") );		
		btnAddSock 		= new JIconButton( new ImageIcon("images/add.png"), new ImageIcon("images/add_over.png"), new ImageIcon("images/add_down.png") );	
		btnDelSock 		= new JIconButton( new ImageIcon("images/del.png"), new ImageIcon("images/del_over.png"), new ImageIcon("images/del_down.png") );	
		btnSaveSock 	= new JIconButton( new ImageIcon("images/save.png"), new ImageIcon("images/save_over.png"), new ImageIcon("images/save_down.png") );
		btnReadSock 	= new JIconButton( new ImageIcon("images/open.png"),new ImageIcon("images/open_over.png"), new ImageIcon("images/open_down.png") );
		
		combModelSockChoix 	= new DefaultComboBoxModel<String>(this.oModSock.getArraySockets());
		combSockChoix 		= new JComboBox<String>(combModelSockChoix);
		
		btnAddSock.setToolTipText("Ajouter un Socket");
		btnConnect.setToolTipText("Se connecter au Socket sélectionné");
		btnDelSock.setToolTipText("Supprimer le Socket sélectionné");
		btnReadSock.setToolTipText("Charger les Sockets du fichier");
		btnSaveSock.setToolTipText("Sauvegarder les Sockets");
		combSockChoix.setToolTipText("Sélectionnez un Socket");
		lblVoltage.setToolTipText("Voltage de la batterie");
		
		
		add( combSockChoix );
		add( btnConnect );
		add( btnAddSock );
		add( btnDelSock );
		add( btnSaveSock );
		add( btnReadSock );
		add( lblVoltage );
//		add( new JButton("Lina-Joy") );
//		add( new JButton("Flora") );
//		add( new JButton("papa") );
//		add( new JButton("maman") );
		
		
		
		
	}


	@Override
	public void update(Observable o, Object message) {
		if( o == oModSock ){
			if( message.equals("SOCKETADDED") || message.equals("SOCKETDELETED") ){
				if( Debug.isEnable() )
					System.out.println( "ConnectPanel : CamAdded" );
				combModelSockChoix = new DefaultComboBoxModel<String>(this.oModSock.getArraySockets());
				combSockChoix.setModel(combModelSockChoix);
			}
			else if( message.equals( "SOCKETCONNECTION") ){
				if( oModSock.isConnected() )
					btnConnect.setDown();
				else
					btnConnect.setUp();
			}
				
			
		}else if( o == oModCtrl ){
			if( message.equals("VOLTAGE") )
				lblVoltage.setText(oModCtrl.getVoltage()+"v");

		}
		
		
	}
	
	public void setListener( ActionListener ac){		
		combSockChoix.addActionListener( ac );
		combSockChoix.setActionCommand("SELECTEDSOCKET");
		
		btnConnect.addActionListener( ac );
		btnConnect.setActionCommandUP("BTNCONNECTSOCKET");
		btnConnect.setActionCommandDOWN("BTNSTOPSOCKET");
			
		btnAddSock.addActionListener( ac );
		btnAddSock.setActionCommand("BTNADDSOCKET");
		
		btnDelSock.addActionListener( ac );
		btnDelSock.setActionCommand("BTNDELSOCKET");
		
		btnSaveSock.addActionListener( ac );
		btnSaveSock.setActionCommand("BTNSAVESOCKETS");
		
		btnReadSock.addActionListener( ac );
		btnReadSock.setActionCommand("BTNREADSOCKETS");
		

	}
	public void setKeyListener( KeyListener cpCtrlPil ){
		this.addKeyListener(cpCtrlPil);
	}
	
	public int getSelectedSocket(){
		return combSockChoix.getSelectedIndex();
	}
	
}
