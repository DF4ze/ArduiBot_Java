package controleurs;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modeles.CtrlCat;
import modeles.GraphPilotCat;
import modeles.KeyCat;
import net.java.games.input.Component.Identifier;
import vues.CamFrame;
import controleurs.inputs.FixedMotionMouse;

public class ControleurPilotage implements MouseListener, MouseMotionListener, ChangeListener, KeyListener, Observer{

	private CamFrame cfFrame;
	private CtrlCat oModCtrl;
	private GraphPilotCat oModGraph;
	private KeyCat oModKey;
	
	private HashMap<String, Component>  directionBtn;
	private HashMap<String, Component>  tourelleBtn;
	
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
			fmmDirection = new FixedMotionMouse(false, true);
			fmmTourelle = new FixedMotionMouse(false, false);
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
		}else if( directionBtn.get("checkbox").equals(e.getSource()) && oModCtrl.isDirectionEnable() ){
			JCheckBox cb = (JCheckBox)directionBtn.get("checkbox");
			if( Debug.isEnable() )
				System.out.println("CheckBox Dir "+cb.isSelected());
			
			oModCtrl.setReverseYDir(cb.isSelected());
			
		}else if( tourelleBtn.get("checkbox").equals(e.getSource()) && oModCtrl.isTourelleEnable() ){
			JCheckBox cb = (JCheckBox)tourelleBtn.get("checkbox");
			if( Debug.isEnable() )
				System.out.println("CheckBox Tour "+cb.isSelected());
			
			oModCtrl.setReverseYTour(cb.isSelected());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		boolean bDir = false;
		if( oModCtrl.isDirectionEnable() ){
			for(Entry<String, Component> entry : directionBtn.entrySet() ) {
			    String sDir = entry.getKey();
			    Component button = entry.getValue();
	
			    if( button.equals(e.getSource()) && (button instanceof JButton) ){
			    	bDir = true;
			    	oModGraph.setDirectionOver(sDir);
			    	break;
			    }
			}
		}
		if( !bDir ){
			if( oModCtrl.isTourelleEnable() ){
				for(Entry<String, Component> entry : tourelleBtn.entrySet() ) {
					String sDir = entry.getKey();
					Component button = entry.getValue();
					
					if( button.equals(e.getSource()) && (button instanceof JButton) ){
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
				for(Entry<String, Component> entry : directionBtn.entrySet() ) {
					Component button = entry.getValue();
		
				    if( button.equals(e.getSource()) && (button instanceof JButton) ){
				    	bDir = true;
				    	break;
				    }
				}
			}
			if( !bDir ){
				if( oModCtrl.isTourelleEnable() ){
					for(Entry<String, Component> entry : tourelleBtn.entrySet() ) {
						Component button = entry.getValue();
						
						if( button.equals(e.getSource()) && (button instanceof JButton) ){
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
			for(Entry<String, Component> entry : directionBtn.entrySet() ) {
			    String sDir = entry.getKey();
			    Component button = entry.getValue();
	
			    if( button.equals(e.getSource()) && (button instanceof JButton)){
			    	bDir = true;
			    	oModGraph.setDirectionOrientation(sDir);
			    	sDirection = sDir;
			    	break;
			    }
			}
		}
		if( !bDir )
			if( oModCtrl.isTourelleEnable() ){
				for(Entry<String, Component> entry : tourelleBtn.entrySet() ) {
					String sDir = entry.getKey();
					Component button = entry.getValue();
					
					if( button.equals(e.getSource()) && (button instanceof JButton) ){
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
			for(Entry<String, Component> entry : directionBtn.entrySet() ) {
				String sDir = entry.getKey();
				Component button = entry.getValue();
	
			    if( button.equals(e.getSource()) && (button instanceof JButton) ){
			    	oModGraph.setDirectionOver(sDir);
			    	bDir = true;
			    	break;
			    }
			}
		}
		if( !bDir ){
			if( oModCtrl.isTourelleEnable() ){
				for(Entry<String, Component> entry : tourelleBtn.entrySet() ) {
					String sDir = entry.getKey();
					Component button = entry.getValue();
					
					if( button.equals(e.getSource()) && (button instanceof JButton) ){
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
				if( oModCtrl.isReverseYDir() )
					oModGraph.setDirectionOrientation("downleft");
				else
					oModGraph.setDirectionOrientation("upleft");
				
			}else if( fmmDirection.getTotalMovedY() < -1 && fmmDirection.getTotalMovedX() >= -1 && fmmDirection.getTotalMovedX() <= 1){
				if( oModCtrl.isReverseYDir() )
					oModGraph.setDirectionOrientation("down");
				else
					oModGraph.setDirectionOrientation("up");
				
			}else if( fmmDirection.getTotalMovedY() < -1 && fmmDirection.getTotalMovedX() > 1 ){
				if( oModCtrl.isReverseYDir() )
					oModGraph.setDirectionOrientation("downright");
				else
					oModGraph.setDirectionOrientation("upright");
				
			}else if( fmmDirection.getTotalMovedY() <= 1 && fmmDirection.getTotalMovedY() >= -1 && fmmDirection.getTotalMovedX() > 1 ){
				oModGraph.setDirectionOrientation("right");
				
			}else if( fmmDirection.getTotalMovedY() <= 1 && fmmDirection.getTotalMovedY() >= -1 && fmmDirection.getTotalMovedX() < -1 ){
				oModGraph.setDirectionOrientation("left");
				
			}else if( fmmDirection.getTotalMovedY() > 1  && fmmDirection.getTotalMovedX() < -1 ){
				if( oModCtrl.isReverseYDir() )
					oModGraph.setDirectionOrientation("upleft");
				else
					oModGraph.setDirectionOrientation("downleft");
				
			}else  if( fmmDirection.getTotalMovedY() > 1  && fmmDirection.getTotalMovedX() > 1 ){
				if( oModCtrl.isReverseYDir() )
					oModGraph.setDirectionOrientation("upright");
				else
					oModGraph.setDirectionOrientation("downright");
				
			}else if( fmmDirection.getTotalMovedY() > 1  && fmmDirection.getTotalMovedX() <= 1 && fmmDirection.getTotalMovedX() >= -1 ){
				if( oModCtrl.isReverseYDir() )
					oModGraph.setDirectionOrientation("up");
				else
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
				if( oModCtrl.isReverseYTour() )
					oModGraph.setTourelleOrientation("downleft");
				else
					oModGraph.setTourelleOrientation("upleft");
				
			}else if( fmmTourelle.getTotalMovedY() < -1 && fmmTourelle.getTotalMovedX() >= -1 && fmmTourelle.getTotalMovedX() <= 1){
				if( oModCtrl.isReverseYTour() )
					oModGraph.setTourelleOrientation("down");
				else
					oModGraph.setTourelleOrientation("up");
				
			}else if( fmmTourelle.getTotalMovedY() < -1 && fmmTourelle.getTotalMovedX() > 1 ){
				if( oModCtrl.isReverseYTour() )
					oModGraph.setTourelleOrientation("downright");
				else
					oModGraph.setTourelleOrientation("upright");
				
			}else if( fmmTourelle.getTotalMovedY() <= 1 && fmmTourelle.getTotalMovedY() >= -1 && fmmTourelle.getTotalMovedX() > 1 ){
				oModGraph.setTourelleOrientation("right");
				
			}else if( fmmTourelle.getTotalMovedY() <= 1 && fmmTourelle.getTotalMovedY() >= -1 && fmmTourelle.getTotalMovedX() < -1 ){
				oModGraph.setTourelleOrientation("left");
				
			}else if( fmmTourelle.getTotalMovedY() > 1  && fmmTourelle.getTotalMovedX() < -1 ){
				if( oModCtrl.isReverseYTour() )
					oModGraph.setTourelleOrientation("upleft");
				else
					oModGraph.setTourelleOrientation("downleft");
				
			}else  if( fmmTourelle.getTotalMovedY() > 1  && fmmTourelle.getTotalMovedX() > 1 ){
				if( oModCtrl.isReverseYTour() )
					oModGraph.setTourelleOrientation("upright");
				else
					oModGraph.setTourelleOrientation("downright");
				
			}else if( fmmTourelle.getTotalMovedY() > 1  && fmmTourelle.getTotalMovedX() <= 1 && fmmTourelle.getTotalMovedX() >= -1 ){
				if( oModCtrl.isReverseYTour() )
					oModGraph.setTourelleOrientation("up");
				else
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






	@Override
	public void update(Observable oModJoy, Object compo) {
		net.java.games.input.Component component = (net.java.games.input.Component)compo;
        Identifier componentIdentifier = component.getIdentifier();
        
        // Buttons
        //if(component.getName().contains("Button")){ // If the language is not english, this won't work.
        if(componentIdentifier.getName().matches("^[0-9]*$")){ // If the component identifier name contains only numbers, then this is a button.
        	if(component.getPollData() == 0.0f)
        		;
        	
        	
        }else if(component.isAnalog()){
            float axisValue = component.getPollData();
            int axisValueForSlider = (int)(axisValue*255); // il va falloir mettre le 255 en constante quelque part!!!
            
            // Left Stick
            // X axis
            if(componentIdentifier == net.java.games.input.Component.Identifier.Axis.X){
            	oModGraph.setHoriSliderDirPos( Math.abs(axisValueForSlider) );
            	oModGraph.setDirectionOrientation(((JoystickCat) oModJoy).getDirWay());
            	//continue; // Go to next component.
            }
            // Y axis
            if(componentIdentifier == net.java.games.input.Component.Identifier.Axis.Y){
            	oModGraph.setVertSliderDirPos( Math.abs(axisValueForSlider) );
                //continue; // Go to next component.
            }
            
            // Right Stick
            // X axis
            if(componentIdentifier == net.java.games.input.Component.Identifier.Axis.SLIDER){
            	oModGraph.setVertSliderTourPos( Math.abs(axisValueForSlider) );
                //continue; // Go to next component.
            }
            // Y axis
            if(componentIdentifier == net.java.games.input.Component.Identifier.Axis.RZ){
            	oModGraph.setHoriSliderTourPos( Math.abs(axisValueForSlider) );
                //continue; // Go to next component.
            }
            
            // Other axis
//            JLabel progressBarLabel = new JLabel(component.getName());
//            JProgressBar progressBar = new JProgressBar(0, 100);
//            progressBar.setValue(axisValueInPercentage);
//            axesPanel.add(progressBarLabel);
//            axesPanel.add(progressBar);
        }
	}

}
