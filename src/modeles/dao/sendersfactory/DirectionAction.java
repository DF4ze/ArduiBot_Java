package modeles.dao.sendersfactory;

import java.util.Date;

public class DirectionAction implements IActionCommunication {
	private int vitesse 	= 0;
	private int delta 		= 0;
	private int priority 	= 0;
	private long timeStamp 	= 0;
		
	public DirectionAction() {
		setTimeStamp(0); 
	}

	public DirectionAction( int vitesse, int delta ) {
		this.vitesse = vitesse;
		this.delta = delta;
		setTimeStamp(0); 
	}

	public DirectionAction( int vitesse, int delta, int priority ) {
		this.vitesse = vitesse;
		this.delta = delta;
		this.priority = priority;
		setTimeStamp(0); 
	}
	
	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return IActionCommunication.modeMotor+"."+vitesse+"."+delta;
	}

	@Override
	public long getTimeStamp() {
		// TODO Auto-generated method stub
		return timeStamp;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setTimeStamp(long timestamp) {
		if( timestamp == 0 )
			this.timeStamp = new Date().getTime();
		else
			this.timeStamp = timestamp;
	}

}
