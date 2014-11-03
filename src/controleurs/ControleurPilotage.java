package controleurs;

import java.awt.AWTException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;

import demos.robot.FixedMotionMouse;

import modeles.CtrlCat;
import vues.CamFrame;

public class ControleurPilotage implements MouseListener, MouseMotionListener {

	private CamFrame cfFrame;
	private CtrlCat oModCtrl;
	private HashMap<String, JButton>  directionBtn;
	private HashMap<String, JButton>  tourelleBtn;
	
	private FixedMotionMouse fmmTourelle;
	private FixedMotionMouse fmmDirection;
	
	public ControleurPilotage( CamFrame cfFrame, CtrlCat oModCtrl ) {
		this.cfFrame = cfFrame;
		this.oModCtrl = oModCtrl;
		
		directionBtn = this.cfFrame.getDirectionBtn();
		tourelleBtn = this.cfFrame.getTourelleBtn();
		
		try {
			fmmTourelle = new FixedMotionMouse(false, true);
			fmmDirection = new FixedMotionMouse(false, false);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if( directionBtn.get("center").equals(e.getSource()) && oModCtrl.isDirectionEnable() ){
			if( fmmDirection.isFixedMouse() )
				fmmDirection.releaseMouse();
			else
				fmmDirection.fixMouse(e.getXOnScreen(), e.getYOnScreen());
			
		}else if( tourelleBtn.get("center").equals(e.getSource()) && oModCtrl.isTourelleEnable()){
			if( fmmTourelle.isFixedMouse() )
				fmmTourelle.releaseMouse();
			else
				fmmTourelle.fixMouse(e.getXOnScreen(), e.getYOnScreen());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if( directionBtn.get("center").equals(e.getSource())  && oModCtrl.isDirectionEnable()  ){
			fmmDirection.releaseMouse();
		}else if( tourelleBtn.get("center").equals(e.getSource()) && oModCtrl.isDirectionEnable()  ){
			fmmTourelle.releaseMouse();
		}		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		boolean bDir = false;
		boolean bTour = false;
		String sDirection = null;
		
		if( oModCtrl.isDirectionEnable() ){
			for(Entry<String, JButton> entry : directionBtn.entrySet() ) {
			    String sDir = entry.getKey();
			    JButton button = entry.getValue();
	
			    if( button.equals(e.getSource()) ){
			    	bDir = true;
			    	sDirection = sDir;
			    	break;
			    }
			}
		}
		if( !bDir )
			if( oModCtrl.isTourelleEnable() ){
				for(Entry<String, JButton> entry : tourelleBtn.entrySet() ) {
					String sDir = entry.getKey();
					JButton button = entry.getValue();
					
					if( button.equals(e.getSource()) ){
						bTour = true;
				    	sDirection = sDir;
						break;
					}
				}
			}
		
		if( Debug.isEnable() && (oModCtrl.isTourelleEnable() || oModCtrl.isDirectionEnable()) )
			System.out.println("bouton appuyé "+((bDir)?"Direction":((bTour)?"Tour":"??"))+" vers "+sDirection);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		boolean bDir = false;
		boolean bTour = false;
		String sDirection = null;
		
		if( oModCtrl.isDirectionEnable() ){
			for(Entry<String, JButton> entry : directionBtn.entrySet() ) {
			    String sDir = entry.getKey();
			    JButton button = entry.getValue();
	
			    if( button.equals(e.getSource()) ){
			    	bDir = true;
			    	sDirection = sDir;
			    	break;
			    }
			}
		}
		
		if( !bDir )
			if( oModCtrl.isTourelleEnable() ){
				for(Entry<String, JButton> entry : tourelleBtn.entrySet() ) {
					String sDir = entry.getKey();
					JButton button = entry.getValue();
					
					if( button.equals(e.getSource()) ){
						bTour = true;
				    	sDirection = sDir;
						break;
					}
				}
			}
		
		if( Debug.isEnable() && (oModCtrl.isTourelleEnable() || oModCtrl.isDirectionEnable()) )
			System.out.println("bouton laché "+((bDir)?"Direction":((bTour)?"Tour":"??"))+" vers "+sDirection);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if( oModCtrl.isDirectionEnable() ){
			if( directionBtn.get("center").equals(e.getSource()) ){
				if( Debug.isEnable() )
					System.out.println("Direction Drag x: "+e.getX()+" y:"+e.getY());	
				
							
			}

		}else if( oModCtrl.isTourelleEnable() ){
			if( tourelleBtn.get("center").equals(e.getSource()) ){
				if( Debug.isEnable() )
					System.out.println("Tourelle Drag x: "+e.getX()+" y:"+e.getY());

				
			}

		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if( fmmDirection.isFixedMouse() ){
			fmmDirection.recMotion(e.getXOnScreen(), e.getYOnScreen());
			if( Debug.isEnable() )
				System.out.println("Direction x: "+fmmDirection.getTotalMovedX()+" y:"+fmmDirection.getTotalMovedY());
		
		}else if( fmmTourelle.isFixedMouse() ){
			fmmTourelle.recMotion(e.getXOnScreen(), e.getYOnScreen());
			if( Debug.isEnable() )
				System.out.println("Tourelle x: "+fmmTourelle.getTotalMovedX()+" y:"+fmmTourelle.getTotalMovedY());
		}
	}

}
