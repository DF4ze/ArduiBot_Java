package vues.tools;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class JIconButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public JIconButton(Icon normal, Icon over, Icon pressed) {
		super(normal);
		
		setBorderPainted(false);
	
		setFocusPainted( false );
		setOpaque( false );
		setContentAreaFilled(false);
		setSize(new Dimension(normal.getIconWidth(), normal.getIconHeight()));
		setMargin(new Insets(1,1,1,1));
		
		addMouseListener(new MouseListener() {            
            @Override
            public void mouseReleased(MouseEvent arg0) {
            	setIcon(normal);
            }           
            @Override
            public void mousePressed(MouseEvent arg0) {
            	if( isEnabled() )
            		setIcon(pressed);
            }            
            @Override
            public void mouseExited(MouseEvent arg0) { 
                setIcon(normal);
            }           
            @Override
            public void mouseEntered(MouseEvent arg0) {
            	if( isEnabled() )
            		setIcon(over);
            }           
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });
	}


}
