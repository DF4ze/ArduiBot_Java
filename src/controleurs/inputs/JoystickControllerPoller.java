package controleurs.inputs;

import modeles.inputs.JoystickCat;
import net.java.games.input.Component;
import net.java.games.input.Controller;

public class JoystickControllerPoller extends Thread{
	
    private Controller firstController = null;
    private JoystickCat cModel= null;
    
	public JoystickControllerPoller( JoystickCat cm ) {
		cModel = cm;
		
		firstController = cModel.getController();
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
