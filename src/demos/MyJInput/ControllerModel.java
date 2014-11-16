package demos.MyJInput;


import java.util.Observable;

import net.java.games.input.Component;

public class ControllerModel extends Observable {
	private float[] componentsValue = null;
	

	public ControllerModel() {
	}
	
	public void setComponents(  Component[] components ){
		if( this.componentsValue != null ){
			checkModified( components );
			
		}else{
			System.out.println("Init");
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
