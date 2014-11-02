package demos.bgpanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FrameTestBase extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
    	BgPanel bgPanel = new BgPanel();
        bgPanel.setLayout(new BorderLayout());
        bgPanel.add(new LoginPanel(bgPanel), BorderLayout.CENTER);

        FrameTestBase t = new FrameTestBase();
        t.setContentPane(bgPanel);
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setSize(250, 100);
        t.setVisible(true);
    }
}
