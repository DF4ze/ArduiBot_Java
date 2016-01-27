package vues.campanels.options;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modeles.catalogues.CtrlCat;


public class InfoPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private JLabel lblVoltage;
	private JLabel lblWifiSign;
	private JLabel lblWifiQuality;
	private JLabel lblWifiNoise;
	
	private CtrlCat oModel;
	
	public InfoPanel( CtrlCat oModel ) {
		this.oModel = oModel;
		oModel.addObserver(this);
		
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Infos"));
		GridBagConstraints c = new GridBagConstraints();
		//c.insets = new Insets(0, -5, 0, 0);
		
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lblVoltageDescr = new JLabel("Tension bat. : ");
		lblVoltage = new JLabel("N/A");
		
		JLabel lblWifiSignDescr = new JLabel("Signal : ");
		lblWifiSign = new JLabel("N/A");
		
		JLabel lblWifiQualityDescr = new JLabel("Qualitée : ");
		lblWifiQuality = new JLabel("N/A");
		
		JLabel lblWifiNoiseDescr = new JLabel("Bruit : ");
		lblWifiNoise = new JLabel("N/A");

		lblVoltageDescr.setToolTipText("Tension de la batterie");
		lblVoltage.setToolTipText("Tension de la batterie");
		
		lblWifiSignDescr.setToolTipText("Puissance signal WIFI");
		lblWifiSign.setToolTipText("Puissance signal WIFI");

		lblWifiQualityDescr.setToolTipText("Qualitée signal WIFI");
		lblWifiQuality.setToolTipText("Qualitée signal WIFI");

		lblWifiNoiseDescr.setToolTipText("Bruit signal WIFI");
		lblWifiNoise.setToolTipText("Bruit signal WIFI");

		int iLigne = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = iLigne;
		add(lblVoltageDescr, c);
		c.gridx = 1;
		add(lblVoltage, c);

		iLigne++;
		c.gridx = 0;
		c.gridy = iLigne;
		add(new JPanel(), c);
		
		iLigne++;
		c.gridy = iLigne;
		c.insets = new Insets(0, -10, 0, 0);
		add(new JLabel("WIFI"), c);
		
		iLigne++;
		c.gridx = 0;
		c.gridy = iLigne;
		c.insets = new Insets(0, 0, 0, 0);
		add(lblWifiSignDescr, c);
		c.gridx = 1;
		add(lblWifiSign, c);
		
		iLigne++;
		c.gridx = 0;
		c.gridy = iLigne;
		add(lblWifiQualityDescr, c);
		c.gridx = 1;
		add(lblWifiQuality, c);
		
		iLigne++;
		c.gridx = 0;
		c.gridy = iLigne;
		add(lblWifiNoiseDescr, c);
		c.gridx = 1;
		add(lblWifiNoise, c);
		
	}


	@Override
	public void update(Observable o, Object message) {
		if( o == oModel ){
			if( message.equals("VOLTAGE") )
				lblVoltage.setText(oModel.getVoltage()+"v");

			else if( message.equals("WIFISIGNAL") )
				lblWifiSign.setText(oModel.getWifiSignal());

			else if( message.equals("WIFIQUALITY") )
				lblWifiQuality.setText(oModel.getWifiQuality());

			else if( message.equals("WIFINOISE") )
				lblWifiNoise.setText(oModel.getWifiNoise());

		}
	}
}
