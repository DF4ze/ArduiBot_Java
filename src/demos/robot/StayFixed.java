package demos.robot;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StayFixed extends JFrame {


	private FixedMotionMouse fmm;
	
	public StayFixed() throws HeadlessException, AWTException {
		fmm = new FixedMotionMouse(false, true);
		
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		JButton dragMe = new JButton("DragMe");
		
		panel.add(dragMe);
		
		add(panel);
		
		dragMe.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				fmm.releaseMouse();
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if( fmm.isFixedMouse() )
					fmm.releaseMouse();
				else
					fmm.fixMouse(e.getXOnScreen(), e.getYOnScreen());
				
			}
		});
		
		dragMe.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				fmm.recMotion(e.getXOnScreen(), e.getYOnScreen());
				
				System.out.println("x:"+fmm.getTotalMovedX()+" y:"+fmm.getTotalMovedY());
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				fmm.recMotion(e.getXOnScreen(), e.getYOnScreen());
		
				System.out.println("x:"+fmm.getTotalMovedX()+" y:"+fmm.getTotalMovedY());
			}
		});

		
		setVisible(true);
	}

	public StayFixed(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public StayFixed(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public StayFixed(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws AWTException {
        new StayFixed();
    }

}
