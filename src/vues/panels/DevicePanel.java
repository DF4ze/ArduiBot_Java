package vues.panels;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeles.CtrlCat;

public class DevicePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	private JCheckBox cb;
	private JTextField tf;
	private JButton btnConnect;
	
	private CtrlCat oModel;
	
	
	public DevicePanel( CtrlCat oModel ) {
		this.oModel = oModel;
		
		cb = new JCheckBox("LocalCam");
		tf = new JTextField("Chemin");
		btnConnect = new JButton("Connect");
		
		add( cb );
		add( tf );
		add( btnConnect );
	}


	@Override
	public void update(Observable o, Object arg) {
		if( o == oModel ){
			
		}
		
	}
	
	public void setListener( ActionListener ac){
		cb.addActionListener( ac );
		cb.setActionCommand("CBLOCALCAM");
		
		tf.addActionListener( ac );
		tf.setActionCommand("TFCHEMIN");
		
		btnConnect.addActionListener( ac );
		btnConnect.setActionCommand("BTNCONNECT");
	}
}
