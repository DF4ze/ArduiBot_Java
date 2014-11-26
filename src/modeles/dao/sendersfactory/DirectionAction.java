package modeles.dao.sendersfactory;

import java.io.Serializable;


public class DirectionAction extends GeneralAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4827229773521990841L;
	
	private Integer vitesse 	= null;
	private Integer delta 		= null;
		
	public DirectionAction() {
		super(); 
	}

	public DirectionAction( int vitesse, int delta ) {
		super(); 
		this.vitesse = vitesse;
		this.delta = delta;
	}

	public DirectionAction( int vitesse, int delta, int priority ) {
		super(); 
		this.vitesse = vitesse;
		this.delta = delta;
		setPriority(priority);
	}
	
	@Override
	public String getAction() {
		return IActionCommunication.modeMotor+"."+vitesse+"."+delta;
	}
	public boolean isComplete(){
		boolean bOk = true;
		if( vitesse == null )
			bOk = false;
		else if( delta == null )
			bOk = false;
		
		return bOk;
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
}
