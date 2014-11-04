package controleurs;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modeles.CtrlCat;
import modeles.GraphPilotCat;
import modeles.KeyCat;
import vues.CamFrame;

public class ControleurPilotage implements MouseListener, MouseMotionListener, ChangeListener, KeyListener{

	private CamFrame cfFrame;
	private CtrlCat oModCtrl;
	private GraphPilotCat oModGraph;
	private KeyCat oModKey;
	
	private HashMap<String, JButton>  directionBtn;
	private HashMap<String, JButton>  tourelleBtn;
	
	private HashMap<String, JSlider>  directionSliders;
	private HashMap<String, JSlider>  tourelleSliders;
	
	private FixedMotionMouse fmmTourelle;
	private FixedMotionMouse fmmDirection;
	
	
	
	
	
	public ControleurPilotage( CamFrame cfFrame, CtrlCat oModCtrl, GraphPilotCat oModGraph, KeyCat oModKey ) {
		this.cfFrame = cfFrame;
		this.oModCtrl = oModCtrl;
		this.oModGraph = oModGraph;
		this.oModKey = oModKey;
		
		directionBtn = this.cfFrame.getDirectionBtn();
		tourelleBtn = this.cfFrame.getTourelleBtn();
		
		directionSliders = this.cfFrame.getDirectionSliders();
		tourelleSliders = this.cfFrame.getTourelleSliders();
		
		try {
			fmmDirection = new FixedMotionMouse(true, true);
			fmmTourelle = new FixedMotionMouse(true, true);
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
				directionBtn.get("center").setCursor(oModGraph.getDefaultCursor());
			}else{
				fmmDirection.fixMouse(e.getXOnScreen(), e.getYOnScreen());
				oModGraph.setDirectionOrientation("center");
				directionBtn.get("center").setCursor(oModGraph.getTransparentCursor());
			}
			
		}else if( tourelleBtn.get("center").equals(e.getSource()) && oModCtrl.isTourelleEnable()){
			if( fmmTourelle.isFixedMouse() ){
				fmmTourelle.releaseMouse();
				oModGraph.setTourDefautBg();
				tourelleBtn.get("center").setCursor(oModGraph.getDefaultCursor());
			}else{
				fmmTourelle.fixMouse(e.getXOnScreen(), e.getYOnScreen());
				oModGraph.setTourelleOrientation("center");
				tourelleBtn.get("center").setCursor(oModGraph.getTransparentCursor());
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
		/*if( directionBtn.get("center").equals(e.getSource())  && oModCtrl.isDirectionEnable()  ){
			fmmDirection.releaseMouse();
		}else if( tourelleBtn.get("center").equals(e.getSource()) && oModCtrl.isDirectionEnable()  ){
			fmmTourelle.releaseMouse();
		}else*/{
			
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
		
			// Parametrage graphique : BackGround
			if( fmmDirection.getTotalMovedY() < -1 && fmmDirection.getTotalMovedX() < -1){
				oModGraph.setDirectionOrientation("upleft");
				
			}else if( fmmDirection.getTotalMovedY() < -1 && fmmDirection.getTotalMovedX() >= -1 && fmmDirection.getTotalMovedX() <= 1){
				oModGraph.setDirectionOrientation("up");
				
			}else if( fmmDirection.getTotalMovedY() < -1 && fmmDirection.getTotalMovedX() > 1 ){
				oModGraph.setDirectionOrientation("upright");
				
			}else if( fmmDirection.getTotalMovedY() <= 1 && fmmDirection.getTotalMovedY() >= -1 && fmmDirection.getTotalMovedX() > 1 ){
				oModGraph.setDirectionOrientation("right");
				
			}else if( fmmDirection.getTotalMovedY() <= 1 && fmmDirection.getTotalMovedY() >= -1 && fmmDirection.getTotalMovedX() < -1 ){
				oModGraph.setDirectionOrientation("left");
				
			}else if( fmmDirection.getTotalMovedY() > 1  && fmmDirection.getTotalMovedX() < -1 ){
				oModGraph.setDirectionOrientation("downleft");
				
			}else  if( fmmDirection.getTotalMovedY() > 1  && fmmDirection.getTotalMovedX() > 1 ){
				oModGraph.setDirectionOrientation("downright");
				
			}else if( fmmDirection.getTotalMovedY() > 1  && fmmDirection.getTotalMovedX() <= 1 && fmmDirection.getTotalMovedX() >= -1 ){
				oModGraph.setDirectionOrientation("down");
				
			}else
				oModGraph.setDirectionOrientation("center");
			
			// Sliders
			if( fmmDirection.isAbsoluteX() )
				oModGraph.setHoriSliderDirPos(Math.abs(fmmDirection.getTotalMovedX()));
			
			if( fmmDirection.isAbsoluteY() )
				oModGraph.setVertSliderDirPos(Math.abs(fmmDirection.getTotalMovedY()));
			
			
			
		}else if( fmmTourelle.isFixedMouse() ){
			fmmTourelle.recMotion(e.getXOnScreen(), e.getYOnScreen());
			if( Debug.isEnable() )
				System.out.println("Tourelle x: "+fmmTourelle.getTotalMovedX()+" y:"+fmmTourelle.getTotalMovedY());
			

			// Parametrage graphique
			if( fmmTourelle.getTotalMovedY() < -1 && fmmTourelle.getTotalMovedX() < -1){
				oModGraph.setTourelleOrientation("upleft");
				
			}else if( fmmTourelle.getTotalMovedY() < -1 && fmmTourelle.getTotalMovedX() >= -1 && fmmTourelle.getTotalMovedX() <= 1){
				oModGraph.setTourelleOrientation("up");
				
			}else if( fmmTourelle.getTotalMovedY() < -1 && fmmTourelle.getTotalMovedX() > 1 ){
				oModGraph.setTourelleOrientation("upright");
				
			}else if( fmmTourelle.getTotalMovedY() <= 1 && fmmTourelle.getTotalMovedY() >= -1 && fmmTourelle.getTotalMovedX() > 1 ){
				oModGraph.setTourelleOrientation("right");
				
			}else if( fmmTourelle.getTotalMovedY() <= 1 && fmmTourelle.getTotalMovedY() >= -1 && fmmTourelle.getTotalMovedX() < -1 ){
				oModGraph.setTourelleOrientation("left");
				
			}else if( fmmTourelle.getTotalMovedY() > 1  && fmmTourelle.getTotalMovedX() < -1 ){
				oModGraph.setTourelleOrientation("downleft");
				
			}else  if( fmmTourelle.getTotalMovedY() > 1  && fmmTourelle.getTotalMovedX() > 1 ){
				oModGraph.setTourelleOrientation("downright");
				
			}else if( fmmTourelle.getTotalMovedY() > 1  && fmmTourelle.getTotalMovedX() <= 1 && fmmTourelle.getTotalMovedX() >= -1 ){
				oModGraph.setTourelleOrientation("down");
				
			}else
				oModGraph.setTourelleOrientation("center");
		
			// Sliders
			if( fmmTourelle.isAbsoluteX() )
				oModGraph.setHoriSliderTourPos(Math.abs(fmmTourelle.getTotalMovedX()));
			
			if( fmmTourelle.isAbsoluteY() )
				oModGraph.setVertSliderTourPos(Math.abs(fmmTourelle.getTotalMovedY()));
		}
		
			
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		boolean isDir = false;
		JSlider source= (JSlider)e.getSource();
		
		if (!source.getValueIsAdjusting()) {
			for( Entry<String, JSlider> entry : directionSliders.entrySet() ){
				if( source.equals( entry.getValue() ) ){
					isDir = true;
					switch( entry.getKey() ){
					case "hori" :
						oModGraph.setHoriSliderDirPos(source.getValue());
						break;
					case "verti" :
						oModGraph.setVertSliderDirPos(source.getValue());
						break;
					default:
						;
					}
				}
			}
			if( !isDir ){
				for( Entry<String, JSlider> entry : tourelleSliders.entrySet() ){
					if( source.equals( entry.getValue() ) ){
						isDir = true;
						switch( entry.getKey() ){
						case "hori" :
							oModGraph.setHoriSliderTourPos(source.getValue());
							break;
						case "verti" :
							oModGraph.setVertSliderTourPos(source.getValue());
							break;
						default:
							;
						}
					}
				}			
			}
		}
		
	}






	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if( Debug.isEnable() )
			System.out.println("KeyPressed");
		
		if( oModKey.setPressedKey( e.getKeyCode() )){
			if( oModKey.getLastPanel().equals("dir") )
				oModGraph.setDirectionOrientation(oModKey.getDirWay());
			else if( oModKey.getLastPanel().equals("tour") )
				oModGraph.setTourelleOrientation(oModKey.getTourWay());
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if( Debug.isEnable() )
			System.out.println("KeyReleased");

		if( oModKey.setReleasedKey( e.getKeyCode() )){
			if( oModKey.getLastPanel().equals("dir") )
				oModGraph.setDirectionOrientation(oModKey.getDirWay());
			else if( oModKey.getLastPanel().equals("tour") )
				oModGraph.setTourelleOrientation(oModKey.getTourWay());
		}		
	}

}
