package modeles;

import java.util.Observable;

public class PilotCat extends Observable {

	private boolean dirUpBtnState;
	private boolean dirLeftBtnState;
	private boolean dirCenterBtnState;
	private boolean dirRightBtnState;
	private boolean dirdownBtnState;
	
	private boolean tourUpBtnState;
	private boolean tourLeftBtnState;
	private boolean tourCenterBtnState;
	private boolean tourRightBtnState;
	private boolean tourdownBtnState;
	
	public boolean isDirUpBtnState() {
		return dirUpBtnState;
	}
	public void setDirUpBtnState(boolean dirUpBtnState) {
		this.dirUpBtnState = dirUpBtnState;
		
		setChanged();
		notifyObservers("DIRECTIONBTNSTATE");
	}
	
	public boolean isDirLeftBtnState() {
		return dirLeftBtnState;
	}
	public void setDirLeftBtnState(boolean dirLeftBtnState) {
		this.dirLeftBtnState = dirLeftBtnState;
		setChanged();
		notifyObservers("DIRECTIONBTNSTATE");
	}
	
	public boolean isDirCenterBtnState() {
		return dirCenterBtnState;
	}
	public void setDirCenterBtnState(boolean dirCenterBtnState) {
		this.dirCenterBtnState = dirCenterBtnState;
		setChanged();
		notifyObservers("DIRECTIONBTNSTATE");
	}
	
	public boolean isDirRightBtnState() {
		return dirRightBtnState;
	}
	public void setDirRightBtnState(boolean dirRightBtnState) {
		this.dirRightBtnState = dirRightBtnState;
		setChanged();
		notifyObservers("DIRECTIONBTNSTATE");
	}
	
	public boolean isDirdownBtnState() {
		return dirdownBtnState;
	}
	public void setDirdownBtnState(boolean dirdownBtnState) {
		this.dirdownBtnState = dirdownBtnState;
		setChanged();
		notifyObservers("DIRECTIONBTNSTATE");
	}
	
	
	
	
	public boolean isTourUpBtnState() {
		return tourUpBtnState;
	}
	public void setTourUpBtnState(boolean tourUpBtnState) {
		this.tourUpBtnState = tourUpBtnState;
		setChanged();
		notifyObservers("TOURELLEBTNSTATE");
	}
	
	public boolean isTourLeftBtnState() {
		return tourLeftBtnState;
	}
	public void setTourLeftBtnState(boolean tourLeftBtnState) {
		this.tourLeftBtnState = tourLeftBtnState;
		setChanged();
		notifyObservers("TOURELLEBTNSTATE");
	}
	
	public boolean isTourCenterBtnState() {
		return tourCenterBtnState;
	}
	public void setTourCenterBtnState(boolean tourCenterBtnState) {
		this.tourCenterBtnState = tourCenterBtnState;
		setChanged();
		notifyObservers("TOURELLEBTNSTATE");
	}
	
	public boolean isTourRightBtnState() {
		return tourRightBtnState;
	}
	public void setTourRightBtnState(boolean tourRightBtnState) {
		this.tourRightBtnState = tourRightBtnState;
		setChanged();
		notifyObservers("TOURELLEBTNSTATE");
	}
	
	public boolean isTourdownBtnState() {
		return tourdownBtnState;
	}
	public void setTourdownBtnState(boolean tourdownBtnState) {
		this.tourdownBtnState = tourdownBtnState;
		setChanged();
		notifyObservers("TOURELLEBTNSTATE");
	}
	
}
