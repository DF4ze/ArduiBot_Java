package vues.tools;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BgPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Image defaultBg ;//= new ImageIcon("images/Maps-Center-Direction-icon.png").getImage();
	private Image bg ;//= defaultBg;
	
	public BgPanel( String imageName ){
		defaultBg = new ImageIcon( imageName ).getImage();
		bg = defaultBg;
//		setSize(new Dimension(ii.getIconWidth(), ii.getIconHeight()));
		
	}
	
    @Override
    public void paintComponent(Graphics g) {
    	g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
    
    public void setBG( String bgName ){
    	bg = new ImageIcon(bgName).getImage();
    	this.repaint();
    }
    public void setBgByDefault(){
    	bg = defaultBg;
    	this.repaint();
    }
}
