package modeles.dao.sendersfactory;

public interface ISendable {
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
	
	public String getHurryAction();
	public String getAction();
	public long getTimeStamp();
	public int getPriority();
}
