package demos.MyJInput;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import net.java.games.input.Component;


public class Starter implements Observer{

	public Starter() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// Création du model
		ControllerModel cm = new ControllerModel();
		// Création du Poller
		ControllerPoller cp = new ControllerPoller( cm );
		cp.setDaemon(true);
		
		if( cp.isControllerFound() ){
			// Création de la "vue";
			Starter lc = new Starter();
			cm.addObserver(lc);
			
			// Lancement du poll
			cp.start();
		}else
			System.out.println( "Controller not Found" );

		
		System.out.println( "Press Enter to STOP" );
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		sc.next();
	}

	@Override
	public void update(Observable oModel, Object component) {
		Component compo = ( Component )component;
		System.out.println("Composant "+ compo.getName() +" id : "+ compo.getIdentifier() +" : "+compo.getPollData());
		
	}

}
