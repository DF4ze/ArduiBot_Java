package modeles.dao.sendersfactory;

public interface IActionCommunication {
	// Priorities
	final int prioLow 		= 0;
	final int prioMedium 	= 1;
	final int prioHight 	= 2;
	final int prioHighest 	= 3;
	// Modes
	final int modeMotor		= 1;
	final int modeServo		= 2;
	final int modeLight		= 3;
	final int modeWebcam	= 4;
	final int modeAlim		= 5;
	final int modeDrone		= 6;
	
	// Lights
	final static int Lazer 	= 1;
	final static int Strobe = 2;
	final static int Light 	= 3;
	
	// Alims
	final static int alimArduino = 1;
	final static int alimElectro = 2;
	final static int alimPeriph  = 3;
	
	// modeDrone
	final static int modeDroneAuto 		= 1;
	final static int modeDroneSemi 		= 2;
	final static int modeDroneManuel  	= 3;
	
	
	public String getAction();
	public long getTimeStamp();
	public int getPriority();
}
