package vues.addsockpanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeles.catalogues.SocketCat;
import modeles.entites.SocketModel;

public class BigPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField tfName;
	private JTextField tfURL;
	private JTextField tfPort;

	private JButton btnOK;
	private JButton btnAnnuler;
	
	
	public BigPanel( SocketCat oModSock ) {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Ajouter SocketS"));

		
		// Création des éléments
		tfName = new JTextField();
		JLabel lblName = new JLabel("Nom : ");
		tfURL = new JTextField();
		JLabel lblURL = new JLabel("IP : ");
		tfPort = new JTextField();
		JLabel lblPort = new JLabel("Port : ");
		
		btnOK = new JButton("OK");
		btnAnnuler = new JButton("Annuler");
		
		
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
		
		// IP
		c.gridy = iLigne++;
		c.gridx = 0;
		c.gridwidth = 1;
		add( lblPort, c );
		c.gridx = 1;
		c.gridwidth = 3;
		add( tfPort, c );

		
		// Boutons
		c.gridx = 1;
		c.gridy = iLigne;
		c.gridwidth = 2;
		add( btnOK, c );
		
		c.gridx = 3;
		c.gridwidth = 1;
		add( btnAnnuler, c );
		
	}

	public void setListener( ActionListener ac ){
		btnOK.addActionListener( ac );
		btnOK.setActionCommand("OKADDSOCKET");
		
		btnAnnuler.addActionListener( ac );
		btnAnnuler.setActionCommand("ANNULERADDSOCKET");
	}
	
	public SocketModel getValues(){
		SocketModel sm = new SocketModel();
		
		sm.setIp(tfURL.getText());
		sm.setName(tfName.getText());
		sm.setPort(Integer.valueOf( tfPort.getText() ));
		
		return sm;
	}
}





