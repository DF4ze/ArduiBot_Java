package modeles.inputs;


import java.util.HashMap;
import java.util.Observable;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class JoystickCat extends Observable {
	private Controller firstController = null;
	private HashMap<String, Float> components;

	private final String upWay = "up";
	private final String leftWay = "left";
	private final String rightWay = "right";
	private final String downWay = "down";
	
	
	public JoystickCat() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        
        // First controller of the desired type.
        for(int i=0; i < controllers.length && firstController == null; i++) {
        	if (	controllers[i].getType() == Controller.Type.STICK 	|| 
                    controllers[i].getType() == Controller.Type.GAMEPAD || 
                    controllers[i].getType() == Controller.Type.WHEEL 	||
                    controllers[i].getType() == Controller.Type.FINGERSTICK ) {
                // Found a controller
                firstController = controllers[i];
                
                break;
            }
        }
		
	}
	
	public boolean isControllerFound(){
		boolean bFound = false;
		if( firstController != null )
			bFound = true;
		
		return bFound;
	}
	
	public Controller getController(){
		return firstController;
	}
	public HashMap<String, Float> getComponents(){
		return components;
	}
	
	public void setComponents(  Component[] components ){
		if( this.components != null ){
			checkModified( components );
			
		}else{
			this.components = new HashMap<String, Float>();
			for( Component compo : components ){
				this.components.put(compo.getIdentifier().getName(), compo.getPollData());
			}
		}
	}

	
	protected void checkModified( Component[] components ){
		// on fait le tour des composants du Controller
		for( Component compo : components ){
			String sID = compo.getIdentifier().getName();
			
			// si une valeur est différente
			if( this.components.get(sID) != compo.getPollData() ){

				// On enregistre la nouvelle valeur
				this.components.put(sID, compo.getPollData() );
				
				//on signale la modification
				if( compo.getIdentifier() == Component.Identifier.Axis.X || compo.getIdentifier() == Component.Identifier.Axis.Y ){
					setChanged();
					notifyObservers("DIRECTION");
				}
				
				if( compo.getIdentifier() == Component.Identifier.Axis.RZ || compo.getIdentifier() == Component.Identifier.Axis.SLIDER  ){
					setChanged();
					notifyObservers("TOURELLE");
				}
				
				if( compo.getIdentifier().getName().matches("^[0-9]*$") ){
					setChanged();
					notifyObservers(compo.getIdentifier().getName());					
				}
			}
//			else
//				System.out.println("Not Change");
//				
		}
	}
	
	public String getDirWay( boolean reverseY ){
		String sWay = "";
		if( components.get("y") > 0.01){ // !!! axe des Y est inversé !!!
			sWay = (reverseY)?upWay:downWay;
			if( components.get("x") > 0.01 )
				sWay += rightWay;
			else if( components.get("x") < -0.01 )
				sWay += leftWay;
			
		}else if( components.get("y") < -0.01){ // !!! axe des Y est inversé !!!
			sWay = (reverseY)?downWay:upWay;
			if( components.get("x") > 0.01 )
				sWay += rightWay;
			else if( components.get("x") < -0.01 )
				sWay += leftWay;
			
		}else if( components.get("x") > 0.01 )
			sWay = rightWay;
		
		else if( components.get("x") < -0.01 )
			sWay = leftWay;
		
		
		return sWay;
	}
	
	public String getTourWay( boolean reverseY ){
		String sWay = "";
		if( components.get("slider") > 0.01){ // !!! axe des Y est inversé !!!
			sWay = (reverseY)?upWay:downWay;
			if( components.get("rz") > 0.01 )
				sWay += rightWay;
			else if( components.get("rz") < -0.01 )
				sWay += leftWay;
			
		}else if( components.get("slider") < -0.01){ // !!! axe des Y est inversé !!!
			sWay = (reverseY)?downWay:upWay;
			if( components.get("rz") > 0.01 )
				sWay += rightWay;
			else if( components.get("rz") < -0.01 )
				sWay += leftWay;
			
		}else if( components.get("rz") > 0.01 )
			sWay = rightWay;
		
		else if( components.get("rz") < -0.01 )
			sWay = leftWay;
		
		
		return sWay;
	}
	
	public int axisForSlider( float axisValue){
        return Math.abs((int)(axisValue*255)); // il va falloir mettre le 255 en constante quelque part!!!

	}
}
