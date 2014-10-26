package vues.panels.controls;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ExtraPanel extends JPanel {


	private static final long serialVersionUID = 1L;

	public ExtraPanel() {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createTitledBorder("Options"));
		
		JCheckBox cbLight = new JCheckBox("Light");
		JCheckBox cbStrob = new JCheckBox("Strob");
		JCheckBox cbLazer = new JCheckBox("Lazer");

		add(cbLight);
		add(cbStrob);
		add(cbLazer);	
		
	}

}
