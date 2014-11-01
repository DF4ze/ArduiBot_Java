package vues.tools;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class JIconSwitchButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean up = true;
	private String actionCommandUP;
	private String actionCommandDOWN;

	public JIconSwitchButton(Icon normalUP, Icon overUP, Icon pressedUP, Icon normalDOWN, Icon overDOWN, Icon pressedDOWN ) {
		super(normalUP);
		
		setBorderPainted(false);
	
		setFocusPainted( false );
		setOpaque( false );
		setContentAreaFilled(false);
		setSize(new Dimension(normalUP.getIconWidth(), normalUP.getIconHeight()));
		setMargin(new Insets(1,1,1,1));
		
		addMouseListener(new MouseListener() {            
            @Override
            public void mouseReleased(MouseEvent arg0) {
            	if( isEnabled() ){
            		up = !up;
            	
	            	if( up ){
	            		setIcon(overUP);
	            		setActionCommand(actionCommandUP);

	            	}else{
	            		setIcon(overDOWN);
	            		setActionCommand(actionCommandDOWN);

	            	}
            	}
            }           
            @Override
            public void mousePressed(MouseEvent arg0) {
            	if( isEnabled() ){
            		if( up )
            			setIcon(pressedUP);
            		else
            			setIcon(pressedDOWN);
            	}
            }            
            @Override
            public void mouseExited(MouseEvent arg0) { 
           		if( up )
        			setIcon(normalUP);
        		else
        			setIcon(normalDOWN);
            }           
            @Override
            public void mouseEntered(MouseEvent arg0) {
            	if( isEnabled() ){
               		if( up )
            			setIcon(overUP);
            		else
            			setIcon(overDOWN);
            	}
            }           
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });
	}

	public boolean isUp(){
		return up;
	}

	public String getActionCommandUP() {
		return actionCommandUP;
	}
	public void setActionCommandUP(String actionCommandUP) {
		this.actionCommandUP = actionCommandUP;
		setActionCommand(actionCommandUP);
	}

	public String getActionCommandDOWN() {
		return actionCommandDOWN;
	}
	public void setActionCommandDOWN(String actionCommandDOWN) {
		this.actionCommandDOWN = actionCommandDOWN;
	}

}
