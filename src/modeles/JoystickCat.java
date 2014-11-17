package modeles;


import java.util.HashMap;
import java.util.Observable;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class JoystickCat extends Observable {
	private float[] componentsValue = null;
	private Controller firstController = null;
	private HashMap<String, Float> components = null;

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
//			this.componentsValue = new float[components.length];
//			for( int i = 0; i < components.length; i++ ){
//				this.componentsValue[i] = components[i].getPollData();
//			}
			
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
				setChanged();
				if( compo.getIdentifier() == Component.Identifier.Axis.X || compo.getIdentifier() == Component.Identifier.Axis.Y )
					notifyObservers("DIRECTION");
				//.........
			}
//			else
//				System.out.println("Not Change");
//				
		}
	}
	
	public String getDirWay(){
		
		return "";
	}
	
	public String getTourWay(){
		
		return "";		
	}
}
