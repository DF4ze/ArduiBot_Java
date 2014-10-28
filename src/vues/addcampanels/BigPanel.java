package vues.addcampanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import vues.tools.PlaceholderTextField;

public class BigPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private PlaceholderTextField tfName;
	private PlaceholderTextField tfURL;
	private JComboBox<String> combMode;
	private JCheckBox chkbMode;
	private PlaceholderTextField tfHeight;
	private PlaceholderTextField tfWidth;
	private JCheckBox chkbSize;
	private JButton btnOK;
	private JButton btnAnnuler;
	
	
	public BigPanel() {
		setLayout(new GridBagLayout());
		
		// Création des éléments
		tfName = new PlaceholderTextField("Nom de la caméra");
		tfURL = new PlaceholderTextField("URL de la caméra");
		tfHeight = new PlaceholderTextField("Hauteur de la caméra");
		tfWidth = new PlaceholderTextField("Largeur de la caméra");
		
		combMode = new JComboBox<String>(new String[]{"PUSH", "PULL"});
		
		chkbMode = new JCheckBox();
		chkbSize = new JCheckBox();
		
		btnOK = new JButton("OK");
		btnAnnuler = new JButton("Annuler");
		
		tfHeight.setEnabled(false);
		tfWidth.setEnabled(false);
		combMode.setEnabled(false);
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		int iLigne = 0;
		c.gridx = 1;
		c.gridy = iLigne++;
		c.gridwidth = 2;
		c.gridheight = 1;
		add( tfName, c );
		
		c.gridy = iLigne++;
		add( tfURL, c );
		
		c.gridy = iLigne;
		c.gridx = 0;
		c.gridwidth = 1;
		add( chkbMode, c );
		
		c.gridx = 1;
		c.gridy = iLigne++;
		add( combMode, c );
		
		c.gridx = 0;
		c.gridy = iLigne;
		c.gridwidth = 1;
		add( chkbSize, c );
		
		c.gridx = 1;
		c.gridy = iLigne++;
		add( tfHeight, c );
		
		c.gridx = 1;
		c.gridy = iLigne++;
		add( tfWidth, c );
		
		c.gridx = 0;
		c.gridy = iLigne;
		add( btnOK, c );
		
		c.gridx = 1;
		add( btnAnnuler, c );
		
		
	}

	public void setListener( ActionListener ac ){
		btnOK.addActionListener( ac );
		btnOK.setActionCommand("OKADDCAM");
		
		btnAnnuler.addActionListener( ac );
		btnAnnuler.setActionCommand("ANNULERADDCAM");
	}
}
