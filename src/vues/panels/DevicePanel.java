package vues.panels;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DevicePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public DevicePanel() {
		// TODO Auto-generated constructor stub
		
		JCheckBox cb = new JCheckBox("LocalCam");
		
		JTextField tf = new JTextField("Chemin");
		
		add( cb );
		add( tf );
	}


}
