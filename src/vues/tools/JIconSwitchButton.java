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
	private boolean entered = false;
	private String actionCommandUP;
	private String actionCommandDOWN;
	
	private Icon normalUP;
	private Icon normalDOWN;

	public JIconSwitchButton(final Icon normalUP, final Icon overUP, final Icon pressedUP, final Icon normalDOWN, final Icon overDOWN, final Icon pressedDOWN ) {
		super(normalUP);
		
		this.normalDOWN = normalDOWN;
		this.normalUP = normalUP;
		
		setBorderPainted(false);
	
		setFocusPainted( false );
		setOpaque( false );
		setContentAreaFilled(false);
		setSize(new Dimension(normalUP.getIconWidth(), normalUP.getIconHeight()));
		setMargin(new Insets(1,1,1,1));
		
		addMouseListener(new MouseListener() {            
            @Override
            public void mouseReleased(MouseEvent arg0) {
            	if( isEnabled() && entered ){
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
           		
           		entered = false;
            }           
            @Override
            public void mouseEntered(MouseEvent arg0) {
            	if( isEnabled() ){
               		if( up )
            			setIcon(overUP);
            		else
            			setIcon(overDOWN);
               		
               		entered = true;
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
	
	public void setUp(){
		up = true;
		setIcon(normalUP);
		setActionCommand(actionCommandUP);

	}
	public void setDown(){
		up = false;
		setIcon(normalDOWN);
		setActionCommand(actionCommandDOWN);
	}

}
