package modeles;

public class DroneActions {
	
	////// WAYZ
	private final static String upWay = "up";
	private final static String leftWay = "left";
	private final static String rightWay = "right";
	private final static String downWay = "down";

	private final static String uprightWay = "upright";
	private final static String upleftWay = "upleft";
	
	private final static String downrightWay = "downright";
	private final static String downleftWay = "downleft";
	
	////// POWER
	private final static Integer maxVerticalPower = 100;
	private final static Integer minVerticalPower = 0;
	private final static Integer maxHorizontalPower = 100;
	private final static Integer minHorizontalPower = 0;
	
	////// BINDED KEY
	//// Direction
	// code
	public final static Integer dirUpCode = 90;			// Z
	public final static Integer dirLeftCode = 81;		// Q
	public final static Integer dirRightCode = 68;		// D
	public final static Integer dirDownCode = 83;		// S

	//// Tourelle
	// code
	public final static Integer tourUpCode = 38;		// fleche haut
	public final static Integer tourLeftCode = 37;		// gauche
	public final static Integer tourRightCode = 39;		// ...
	public final static Integer tourDownCode = 40;		// ...
	
	public DroneActions() {
		// TODO Auto-generated constructor stub
	}

}
