package vues.tools;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class JMapButton extends JButton {

	private static final long serialVersionUID = 1L;

	public JMapButton(BgPanel bgp, String imgNameDown, String imgNameOver ) {
		setFocusPainted( false );
		setOpaque( false );
		setContentAreaFilled(false);
		setSize(new Dimension(20, 20));
		setBorderPainted(false);
		setMargin(new Insets(15,20,15,20));

		addMouseListener(new MouseListener() {            
            @Override
            public void mouseReleased(MouseEvent arg0) {
            	if( isEnabled() )bgp.setBG(imgNameOver);
            }           
            @Override
            public void mousePressed(MouseEvent arg0) {
            	if( isEnabled() )bgp.setBG(imgNameDown);
            }            
            @Override
            public void mouseExited(MouseEvent arg0) { 
            	if( isEnabled() )bgp.setBgByDefault();
            }           
            @Override
            public void mouseEntered(MouseEvent arg0) {
            	if( isEnabled() )bgp.setBG(imgNameOver);
            }           
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });
	}

}
