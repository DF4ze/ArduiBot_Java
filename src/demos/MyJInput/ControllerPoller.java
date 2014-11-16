package demos.MyJInput;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class ControllerPoller extends Thread{
	
    private Controller firstController = null;
    private ControllerModel cModel= null;
    
	public ControllerPoller( ControllerModel cm ) {
		cModel = cm;
		
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        
        // First controller of the desired type.
        for(int i=0; i < controllers.length && firstController == null; i++) {
            if(controllers[i].getType() == Controller.Type.GAMEPAD) {
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

	@Override
	public void run() {
		while(true) {
			//System.out.println("Poll");
            firstController.poll();
            Component[] components = firstController.getComponents();
            
            cModel.setComponents(components);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}

}
