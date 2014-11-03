package controleurs;

import java.awt.AWTException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;

import modeles.CtrlCat;
import modeles.GraphPilotCat;
import vues.CamFrame;

public class ControleurPilotage implements MouseListener, MouseMotionListener {

	private CamFrame cfFrame;
	private CtrlCat oModCtrl;
	private GraphPilotCat oModGraph;
	
	private HashMap<String, JButton>  directionBtn;
	private HashMap<String, JButton>  tourelleBtn;
	
	private FixedMotionMouse fmmTourelle;
	private FixedMotionMouse fmmDirection;
	
	public ControleurPilotage( CamFrame cfFrame, CtrlCat oModCtrl, GraphPilotCat oModGraph ) {
		this.cfFrame = cfFrame;
		this.oModCtrl = oModCtrl;
		this.oModGraph = oModGraph;
		
		directionBtn = this.cfFrame.getDirectionBtn();
		tourelleBtn = this.cfFrame.getTourelleBtn();
		
		try {
			fmmTourelle = new FixedMotionMouse(false, false);
			fmmDirection = new FixedMotionMouse(false, true);
		} catch (AWTException e) {
			if( Debug.isEnable() )
				e.printStackTrace();
		}
		
		this.oModGraph.setDirectionEnable(oModCtrl.isDirectionEnable());
		this.oModGraph.setTourelleEnable(oModCtrl.isTourelleEnable());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if( directionBtn.get("center").equals(e.getSource()) && oModCtrl.isDirectionEnable() ){
			if( fmmDirection.isFixedMouse() ){
				fmmDirection.releaseMouse();
				oModGraph.setDirDefautBg();
			}else{
				fmmDirection.fixMouse(e.getXOnScreen(), e.getYOnScreen());
				oModGraph.setDirectionOrientation("center");
			}
			
		}else if( tourelleBtn.get("center").equals(e.getSource()) && oModCtrl.isTourelleEnable()){
			if( fmmTourelle.isFixedMouse() ){
				fmmTourelle.releaseMouse();
				oModGraph.setTourDefautBg();
			}else{
				fmmTourelle.fixMouse(e.getXOnScreen(), e.getYOnScreen());
				oModGraph.setTourelleOrientation("center");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		boolean bDir = false;
		if( oModCtrl.isDirectionEnable() ){
			for(Entry<String, JButton> entry : directionBtn.entrySet() ) {
			    String sDir = entry.getKey();
			    JButton button = entry.getValue();
	
			    if( button.equals(e.getSource()) ){
			    	bDir = true;
			    	oModGraph.setDirectionOver(sDir);
			    	break;
			    }
			}
		}
		if( !bDir ){
			if( oModCtrl.isTourelleEnable() ){
				for(Entry<String, JButton> entry : tourelleBtn.entrySet() ) {
					String sDir = entry.getKey();
					JButton button = entry.getValue();
					
					if( button.equals(e.getSource()) ){
						oModGraph.setTourelleOver(sDir);
						break;
					}
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if( directionBtn.get("center").equals(e.getSource())  && oModCtrl.isDirectionEnable()  ){
			fmmDirection.releaseMouse();
		}else if( tourelleBtn.get("center").equals(e.getSource()) && oModCtrl.isDirectionEnable()  ){
			fmmTourelle.releaseMouse();
		}else{
			
			boolean bDir = false;
			boolean bTour = false;
			
			if( oModCtrl.isDirectionEnable() ){
				for(Entry<String, JButton> entry : directionBtn.entrySet() ) {
				    JButton button = entry.getValue();
		
				    if( button.equals(e.getSource()) ){
				    	bDir = true;
				    	break;
				    }
				}
			}
			if( !bDir ){
				if( oModCtrl.isTourelleEnable() ){
					for(Entry<String, JButton> entry : tourelleBtn.entrySet() ) {
						JButton button = entry.getValue();
						
						if( button.equals(e.getSource()) ){
							bTour = true;
							break;
						}
					}
				}			
			}
			
			if( bDir ){
				oModGraph.setDirDefautBg();
			}else if ( bTour ){
				oModGraph.setTourDefautBg();
			}
			
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
			    	oModGraph.setDirectionOrientation(sDir);
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
						oModGraph.setTourelleOrientation(sDir);
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
			    	oModGraph.setDirectionOver(sDir);
			    	bDir = true;
			    	break;
			    }
			}
		}
		if( !bDir ){
			if( oModCtrl.isTourelleEnable() ){
				for(Entry<String, JButton> entry : tourelleBtn.entrySet() ) {
					String sDir = entry.getKey();
					JButton button = entry.getValue();
					
					if( button.equals(e.getSource()) ){
				    	oModGraph.setTourelleOver(sDir);
						bTour = true;
						break;
					}
				}
			}			
		}
		
		if( Debug.isEnable() && (oModCtrl.isTourelleEnable() || oModCtrl.isDirectionEnable()) )
			System.out.println("bouton laché "+((bDir)?"Direction":((bTour)?"Tour":"??"))+" vers "+sDirection);
	}

	@Override
	public void mouseDragged(MouseEvent e) {

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
