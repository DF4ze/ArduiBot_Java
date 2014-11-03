package demos.bgpanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

class LoginPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LoginPanel( final BgPanel bg ) {
        setOpaque(false);
        setLayout(new FlowLayout());
        
        JButton jb = new JButton("clic");
        jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bg.setBG( "images/Maps-North-Direction-icon.png" );
				
			}
		});
        add(jb);
    }
}
