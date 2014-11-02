package demos.bgpanel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class BgPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image bg = new ImageIcon("images/Maps-Center-Direction-icon.png").getImage();
    @Override
    public void paintComponent(Graphics g) {
    	g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
    
    public void setBG( String bgName ){
    	bg = new ImageIcon(bgName).getImage();
    	this.repaint();
    }
}
