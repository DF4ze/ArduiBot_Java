package controleurs.inputs;

import java.awt.AWTException;
import java.awt.Robot;

public class FixedMotionMouse {

	private int totalMovedX = 0;
	private int totalMovedY = 0;
	private int initY;
	private int initX;
	private Robot robot;
	private boolean correcting = false;
	private boolean fixedMouse = false;
	private boolean absoluteX;
	private boolean absoluteY;
	
	public FixedMotionMouse(boolean absoluteX, boolean absoluteY) throws AWTException {
		robot = new Robot();	
		this.absoluteX = absoluteX;
		this.absoluteY = absoluteY;
	}
	
	public void fixMouse( int XOnScreen, int YOnScreen ){
		setTotalMovedX(0);
		setTotalMovedY(0);
		initX = XOnScreen;
		initY = YOnScreen;

		fixedMouse = true;
		
	}
	public void releaseMouse(){
		setTotalMovedX(0);
		setTotalMovedY(0);
		
		fixedMouse = false;
	}
	
	public void recMotion( int XOnScreen, int YOnScreen ){
		if( fixedMouse ){
			if( !correcting ){

				if( !absoluteX )
					setTotalMovedX(0);
				if( !absoluteY )
					setTotalMovedY(0);
				
				
				setTotalMovedX(getTotalMovedX() + ( XOnScreen - initX ));
				setTotalMovedY(getTotalMovedY() + ( YOnScreen - initY ));
				
				robot.mouseMove(initX, initY);
				correcting = true;
			
			}else
				correcting = false;
		}
	}

	public int getTotalMovedX() {
		return totalMovedX;
	}
	protected void setTotalMovedX(int totalMovedX) {
		this.totalMovedX = totalMovedX;
	}

	public int getTotalMovedY() {
		return totalMovedY;
	}
	protected void setTotalMovedY(int totalMovedY) {
		this.totalMovedY = totalMovedY;
	}
	
	public boolean isFixedMouse(){
		return fixedMouse;
	}

	public boolean isAbsoluteX(){
		return absoluteX;
	}
	public boolean isAbsoluteY(){
		return absoluteY;
	}
}
