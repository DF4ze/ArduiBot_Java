package modeles;

public class DroneActions {
	
	////// WAYZ
	public final static String upWay = "up";
	public final static String leftWay = "left";
	public final static String rightWay = "right";
	public final static String downWay = "down";

	public final static String uprightWay = "upright";
	public final static String upleftWay = "upleft";
	
	public final static String downrightWay = "downright";
	public final static String downleftWay = "downleft";
	
	////// POWER
	public final static Integer maxVerticalPower = 255;
	public final static Integer minVerticalPower = 0;
	public final static Integer maxHorizontalPower = 255;
	public final static Integer minHorizontalPower = 0;
	
	////// BINDED KEY
	//// Direction
	// code
	public final static int dirUpCode = 90;			// Z
	public final static int dirLeftCode = 81;		// Q
	public final static int dirRightCode = 68;		// D
	public final static int dirDownCode = 83;		// S

	//// Tourelle
	// code
	public final static int tourUpCode = 38;		// fleche haut
	public final static int tourLeftCode = 37;		// gauche
	public final static int tourRightCode = 39;		// ...
	public final static int tourDownCode = 40;		// ...
	
	private DroneActions() {
		
	}

}
