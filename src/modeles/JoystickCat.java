package modeles;


import java.util.Observable;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class JoystickCat extends Observable {
	private float[] componentsValue = null;
	private Controller firstController = null;

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
	
	public void setComponents(  Component[] components ){
		if( this.componentsValue != null ){
			checkModified( components );
			
		}else{
			//System.out.println("Init");
			this.componentsValue = new float[components.length];
			for( int i = 0; i < components.length; i++ ){
				this.componentsValue[i] = components[i].getPollData();
			}
		}
	}

	protected void checkModified( Component[] components ){
		// on fait le tour des composants du Controller
		for( int i = 0; i < components.length; i++ ){
			// si une valeur est différente
			if( this.componentsValue[i] != components[i].getPollData() ){
				// System.out.println("Change");
				// On enregistre la nouvelle valeur
				this.componentsValue[i] = components[i].getPollData();
				
				//on signale la modification
				setChanged();
				notifyObservers(components[i]);
			}
//			else
//				System.out.println("Not Change");
//				
		}
	}
}
