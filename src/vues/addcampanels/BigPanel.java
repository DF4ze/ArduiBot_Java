package vues.addcampanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeles.graphical.CamCat;
import modeles.graphical.camentities.CamDimension;
import modeles.graphical.camentities.CamMode;

public class BigPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfName;
	private JTextField tfURL;
	private JComboBox<CamMode> combMode;
	private JCheckBox chkbMode;
	private JComboBox<CamDimension> combSize;
	private JCheckBox chkbSize;
	private JButton btnOK;
	private JButton btnAnnuler;
	
	
	public BigPanel( CamCat oModCam ) {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Ajouter Caméra"));

		
		// Création des éléments
		tfName = new JTextField();
		JLabel lblName = new JLabel("Nom : ");
		tfURL = new JTextField();
		JLabel lblURL = new JLabel("URL : ");
		combSize = new JComboBox<CamDimension>( oModCam.getCamDimensions());
		
		combMode = new JComboBox<CamMode>(oModCam.getCamModes());
		
		chkbMode = new JCheckBox();
		chkbSize = new JCheckBox();
		
		btnOK = new JButton("OK");
		btnAnnuler = new JButton("Annuler");
		
		combSize.setEnabled(false);
		combMode.setEnabled(false);
		
		
		// Insertion des éléments
		GridBagConstraints c = new GridBagConstraints();
		int iLigne = 0;
		Insets i = new Insets(5, 5, 5, 5);
		c.insets = i;
		
		// Name
		c.gridx = 0;
		c.gridy = iLigne++;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add( lblName, c );
		c.gridx = 1;
		c.gridwidth = 3;
		add( tfName, c );
		
		// URL
		c.gridy = iLigne++;
		c.gridx = 0;
		c.gridwidth = 1;
		add( lblURL, c );
		c.gridx = 1;
		c.gridwidth = 3;
		add( tfURL, c );
		
		// Mode
		c.gridy = iLigne++;
		c.gridx = 0;
		c.gridwidth = 1;
		add( chkbMode, c );
		c.gridx = 1;
		c.gridwidth = 3;
		add( combMode, c );
		
		// Size
		c.gridx = 0;
		c.gridy = iLigne++;
		c.gridwidth = 1;
		add( chkbSize, c );
		c.gridx = 1;
		c.gridwidth = 3;
		add( combSize, c );
		
		// Boutons
		c.gridx = 1;
		c.gridy = iLigne;
		c.gridwidth = 2;
		add( btnOK, c );
		
		c.gridx = 3;
		c.gridwidth = 1;
		add( btnAnnuler, c );
		
		
		// Gestion en local du grisage/dégrisable des comboBox
		chkbMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( combMode.isEnabled() )
					combMode.setEnabled(false);		
				else
					combMode.setEnabled(true);	
			}
		});
		chkbSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( combSize.isEnabled() )
					combSize.setEnabled(false);		
				else
					combSize.setEnabled(true);	
			}
		});
	}

	public void setListener( ActionListener ac ){
		btnOK.addActionListener( ac );
		btnOK.setActionCommand("OKADDCAM");
		
		btnAnnuler.addActionListener( ac );
		btnAnnuler.setActionCommand("ANNULERADDCAM");
	}
	
	public HashMap<String, String> getValues(){
		HashMap<String, String> alDatas = new HashMap<String, String>();
		
		alDatas.put("name", tfName.getText());
		alDatas.put("url", tfURL.getText());
		if( chkbMode.isSelected() )
			alDatas.put( "mode", (String)(combMode.getSelectedItem()) );
		if( chkbSize.isSelected() )
			alDatas.put( "size", (String)(combSize.getSelectedItem()) );
		
		return alDatas;
	}
}





