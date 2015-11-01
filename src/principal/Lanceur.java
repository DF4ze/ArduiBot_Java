package principal;

import controleurs.ControleurGeneral;

public class Lanceur {

	public static void main(String[] args) {
		
		if( args.length != 0 ){
			for( int i = 0; i < args.length; i++ ){
				if( (args[i].equals("-l") || args[i].equals("-lib") || args[i].equals("-libpath")) && i+1 <= args.length ){
					System.setProperty("java.library.path", args[i+1]);
					System.out.println("Libs manually set : "+System.getProperty("java.library.path"));
					i++;
				}
			}
		}
		
		new ControleurGeneral();

	}

}
