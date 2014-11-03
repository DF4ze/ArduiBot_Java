package vues.tools;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;

public class JMapButton extends JButton {

	private static final long serialVersionUID = 1L;

	public JMapButton( ) {
		setFocusPainted( false );
		setOpaque( false );
		setContentAreaFilled(false);
		setSize(new Dimension(20, 20));
		setBorderPainted(false);
		setMargin(new Insets(15,15,15,15));
	}

}
