package modeles.catalogues;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.util.Observable;

import controleurs.Debug;
import modeles.DroneActions;

public class PilotCat extends Observable {
	
	private final String upBtnOVER 		= "images/upOver.png";
	private final String upBtnDOWN 		= "images/upDown.png";
	private final String leftBtnOVER 	= "images/leftOver.png";
	private final String leftBtnDOWN 	= "images/leftDown.png";
	private final String centerBtnOVER 	= "images/centerOver.png";
	private final String centerBtnDOWN 	= "images/centerDown.png";
	private final String rightBtnOVER 	= "images/rightOver.png";
	private final String rightBtnDOWN 	= "images/rightDown.png";
	private final String downBtnOVER 	= "images/downOver.png";
	private final String downBtnDOWN 	= "images/downDown.png";
	
	private final String downLeftBtnOVER	= "images/downLeftOver.png";
	private final String downLeftBtnDOWN	= "images/downLeftDown.png";
	private final String downRightBtnOVER	= "images/downRightOver.png";
	private final String downRightBtnDOWN	= "images/downRightDown.png";
	private final String upRightBtnOVER		= "images/upRightOver.png";
	private final String upRightBtnDOWN		= "images/upRightDown.png";
	private final String upLeftBtnOVER		= "images/upLeftOver.png";
	private final String upLeftBtnDOWN		= "images/upLeftDown.png";
	
	private final String defaultBG 		= "images/default.png";
	private final String disabledBG 	= "images/defaultDisabled.png";
	
	private String bgDir;
	private String bgTour;
	
	private int vertSliderDirPos;
	private int horiSliderDirPos;	
	private int vertSliderTourPos;
	private int horiSliderTourPos;
	
	private int minVertSliderDirPos;
	private int minHoriSliderDirPos;	
	private int minVertSliderTourPos;
	private int minHoriSliderTourPos;
	
	private int maxVertSliderDirPos;
	private int maxHoriSliderDirPos;	
	private int maxVertSliderTourPos;
	private int maxHoriSliderTourPos;
	
	private Cursor transparentCursor;
	private Cursor defaultCursor;
	
	private boolean dirMove;
	private boolean tourMove;
	
	private String dirWay;
	private String tourWay;
	
	public PilotCat(  ){
		
		setDirMove(false);
		setTourMove(false);
		
		vertSliderDirPos = DroneActions.maxVerticalPower/2;
		horiSliderDirPos = DroneActions.maxHorizontalPower/2;	
		vertSliderTourPos = DroneActions.maxVerticalPower/2;
		horiSliderTourPos = DroneActions.maxHorizontalPower/2;	
		
		minVertSliderDirPos = 0;
		minHoriSliderDirPos = 0;	
		minVertSliderTourPos = 0;
		minHoriSliderTourPos = 0;
		
		maxVertSliderDirPos = DroneActions.maxVerticalPower;
		maxHoriSliderDirPos = DroneActions.maxHorizontalPower;	
		maxVertSliderTourPos = DroneActions.maxVerticalPower;
		maxHoriSliderTourPos = DroneActions.maxHorizontalPower;
		
		
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage( new MemoryImageSource(16, 16, pixels, 0, 16));
		
		transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
		defaultCursor = new Cursor( Cursor.DEFAULT_CURSOR );
	}
	
	public String getBgDir(){
		return bgDir;
	}
	public String getBgTour(){
		return bgTour;
	}

	public void setDirDefautBg(){
		bgDir = defaultBG;
		dirMove = false;
		
		setChanged();
		notifyObservers("BGDIRECTION");
	}
	public void setTourDefautBg(){
		bgTour = defaultBG;
		tourMove = false;
		
		setChanged();
		notifyObservers("BGTOURELLE");
	}
	
	public void setTourelleEnable( boolean enable ){
		if( enable )
			bgTour = defaultBG;
		else
			bgTour = disabledBG;
		
		setChanged();
		notifyObservers("BGTOURELLE");
	}
	public void setDirectionEnable( boolean enable ){
		if( enable )
			bgDir = defaultBG;
		else
			bgDir = disabledBG;
		
		setChanged();
		notifyObservers("BGDIRECTION");
	}
	
	public void setDirectionOrientation( String orient ){
		dirMove = true;
		boolean orientValid = false;
		
		switch( orient ){
		case DroneActions.upWay : // UP
			bgDir = upBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.downWay :	// DOWN
			bgDir = downBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.leftWay : // LEFT
			bgDir = leftBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.rightWay :	// RIGHT
			bgDir = rightBtnDOWN;
			orientValid = true;
			break;
		case "center" :	// CENTER
			bgDir = centerBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.upleftWay : // UP LEFT
			bgDir = upLeftBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.uprightWay : // UP RIGHT
			bgDir = upRightBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.downrightWay :	// DOWN RIGHT
			bgDir = downRightBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.downleftWay :	// DOWN LEFT
			bgDir = downLeftBtnDOWN;
			orientValid = true;
			break;
		default :
			dirMove = false;
			bgDir = defaultBG;
		}
		
		if( orientValid )
			setDirWay(orient);
		else 
			setDirWay(DroneActions.centerWay);
		
		setChanged();
		notifyObservers("BGDIRECTION");
	}
	public void setTourelleOrientation( String orient ){
		tourMove = true;
		boolean orientValid = false;
		
		switch( orient ){
		case DroneActions.upWay : // UP
			bgTour = upBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.downWay :	// DOWN
			bgTour = downBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.leftWay : // LEFT
			bgTour = leftBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.rightWay :	// RIGHT
			bgTour = rightBtnDOWN;
			orientValid = true;
			break;
		case "center" :	// CENTER
			bgTour = centerBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.upleftWay : // UP LEFT
			bgTour = upLeftBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.uprightWay : // UP RIGHT
			bgTour = upRightBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.downrightWay :	// DOWN RIGHT
			bgTour = downRightBtnDOWN;
			orientValid = true;
			break;
		case DroneActions.downleftWay :	// DOWN LEFT
			bgTour = downLeftBtnDOWN;
			orientValid = true;
			break;
		default :
			tourMove = false;
			bgTour = defaultBG;
		}
		
		if( orientValid )
			setTourWay(orient);
		else 
			setTourWay(DroneActions.centerWay);

		
		setChanged();
		notifyObservers("BGTOURELLE");

	}
	
	public void setDirectionOver( String orient ){
		switch(orient){
		case DroneActions.upWay :
			bgDir = upBtnOVER;
			break;
		case DroneActions.downWay : 
			bgDir = downBtnOVER;
			break;
		case DroneActions.leftWay : 
			bgDir = leftBtnOVER;
			break;
		case DroneActions.rightWay : 
			bgDir = rightBtnOVER;
			break;
		case "center" : 
			bgDir = centerBtnOVER;
			break;
		case DroneActions.uprightWay : 
			bgDir = upRightBtnOVER;
			break;
		case DroneActions.upleftWay : 
			bgDir = upLeftBtnOVER;
			break;
		case DroneActions.downrightWay : 
			bgDir = downRightBtnOVER;
			break;
		case DroneActions.downleftWay : 
			bgDir = downLeftBtnOVER;
			break;
		default :
			bgDir = defaultBG;
		}
		setDirWay(DroneActions.centerWay);
		
		setChanged();
		notifyObservers("BGDIRECTION");
	}
	public void setTourelleOver( String orient ){
		switch(orient){
		case DroneActions.upWay :
			bgTour = upBtnOVER;
			break;
		case DroneActions.downWay : 
			bgTour = downBtnOVER;
			break;
		case DroneActions.leftWay : 
			bgTour = leftBtnOVER;
			break;
		case DroneActions.rightWay : 
			bgTour = rightBtnOVER;
			break;
		case DroneActions.centerWay : 
			bgTour = centerBtnOVER;
			break;
		case DroneActions.uprightWay : 
			bgTour = upRightBtnOVER;
			break;
		case DroneActions.upleftWay : 
			bgTour = upLeftBtnOVER;
			break;
		case DroneActions.downrightWay : 
			bgTour = downRightBtnOVER;
			break;
		case DroneActions.downleftWay : 
			bgTour = downLeftBtnOVER;
			break;
		default :
			bgTour = defaultBG;
		}
		setTourWay(DroneActions.centerWay);
		
		setChanged();
		notifyObservers("BGTOURELLE");
	}

	
	
	public int getVertSliderDirPos() {
		return vertSliderDirPos;
	}
	public void setVertSliderDirPos(int vertSliderDirPos) {
		this.vertSliderDirPos = vertSliderDirPos;

		if( Debug.isEnable() )
			System.out.println("verti dir " + vertSliderDirPos);

		setChanged();
		notifyObservers("VERTIDIRSLIDER");
	}

	public int getHoriSliderDirPos() {
		return horiSliderDirPos;
	}
	public void setHoriSliderDirPos(int horiSliderDirPos) {
		this.horiSliderDirPos = horiSliderDirPos;
		
		if( Debug.isEnable() )
			System.out.println("hori dir "+horiSliderDirPos);

		setChanged();
		notifyObservers("HORIDIRSLIDER");

	}

	public int getVertSliderTourPos() {
		return vertSliderTourPos;
	}
	public void setVertSliderTourPos(int vertSliderTourPos) {
		this.vertSliderTourPos = vertSliderTourPos;
		
		if( Debug.isEnable() )
			System.out.println("Verti tour " + vertSliderTourPos);

		setChanged();
		notifyObservers("VERTITOURSLIDER");
	}

	public int getHoriSliderTourPos() {
		return horiSliderTourPos;
	}
	public void setHoriSliderTourPos(int horiSliderTourPos) {
		this.horiSliderTourPos = horiSliderTourPos;
		
		if( Debug.isEnable() )
			System.out.println("hori tour " + horiSliderTourPos);
	 	
		setChanged();
		notifyObservers("HORITOURSLIDER");
	}

	public int getMinVertSliderDirPos() {
		return minVertSliderDirPos;
	}
	public int getMinHoriSliderDirPos() {
		return minHoriSliderDirPos;
	}

	public int getMinVertSliderTourPos() {
		return minVertSliderTourPos;
	}
	public int getMinHoriSliderTourPos() {
		return minHoriSliderTourPos;
	}

	public int getMaxVertSliderDirPos() {
		return maxVertSliderDirPos;
	}
	public int getMaxHoriSliderDirPos() {
		return maxHoriSliderDirPos;
	}

	public int getMaxVertSliderTourPos() {
		return maxVertSliderTourPos;
	}
	public int getMaxHoriSliderTourPos() {
		return maxHoriSliderTourPos;
	}

	
	public Cursor getTransparentCursor() {
		return transparentCursor;
	}
	public Cursor getDefaultCursor() {
		return defaultCursor;
	}

	public boolean isDirMove() {
		return dirMove;
	}
	protected void setDirMove(boolean dirMove) {
		this.dirMove = dirMove;
	}

	public boolean isTourMove() {
		return tourMove;
	}
	protected void setTourMove(boolean tourMove) {
		this.tourMove = tourMove;
	}

	public String getDirWay() {
		return dirWay;
	}

	public void setDirWay(String dirWay) {
		this.dirWay = dirWay;
	}

	public String getTourWay() {
		return tourWay;
	}

	public void setTourWay(String tourWay) {
		this.tourWay = tourWay;
	}



}


