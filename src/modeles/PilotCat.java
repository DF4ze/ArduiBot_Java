package modeles;

import java.util.Observable;

import vues.CamFrame;

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
	
	
	private String upBtnOVER = "images/EDirection-North-icon.png";
	private String upBtnDOWN = "images/Maps-North-Direction-icon.png";
	private String leftBtnOVER = "images/EDirection-West-icon.png";
	private String leftBtnDOWN = "images/Maps-West-Direction-icon.png";
	private String centerBtnOVER = "images/EMaps-Center-Direction-icon-over.png";
	private String centerBtnDOWN = "images/EMaps-Center-Direction-icon-clicked.png";
	private String rightBtnOVER = "images/EDirection-East-icon.png";
	private String rightBtnDOWN = "images/Maps-East-Direction-icon.png";
	private String downBtnOVER = "images/EDirection-South-icon.png";
	private String downBtnDOWN = "images/Maps-South-Direction-icon.png";
	private String defaultBG = "images/EMaps-Center-Direction-icon.png";
	private String disabledBG = "images/EMaps-Center-Direction-icon_disabled.png";
	
	private CamFrame cfFrame;
	
	public PilotCat( CamFrame cfFrame ){
		this.cfFrame = cfFrame;
	}
	
	public boolean isDirUpBtnState() {
		return dirUpBtnState;
	}
	public void setDirUpBtnState(boolean dirUpBtnState) {
		this.dirUpBtnState = dirUpBtnState;
		// demander a la vue de mettre le bon background
		
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
