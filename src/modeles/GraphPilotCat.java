package modeles;

import java.util.Observable;

public class GraphPilotCat extends Observable {
	
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
	
	
	public GraphPilotCat(  ){
		
	}
	
	public String getBgDir(){
		return bgDir;
	}
	public String getBgTour(){
		return bgTour;
	}

	public void setDirDefautBg(){
		bgDir = defaultBG;
		
		setChanged();
		notifyObservers("BGDIRECTION");
	}
	public void setTourDefautBg(){
		bgTour = defaultBG;
		
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
		switch( orient ){
		case "up" : // UP
			bgDir = upBtnDOWN;
			break;
		case "down" :	// DOWN
			bgDir = downBtnDOWN;
			break;
		case "left" : // LEFT
			bgDir = leftBtnDOWN;
			break;
		case "right" :	// RIGHT
			bgDir = rightBtnDOWN;
			break;
		case "center" :	// CENTER
			bgDir = centerBtnDOWN;
			break;
		case "upleft" : // UP LEFT
			bgDir = upLeftBtnDOWN;
			break;
		case "upright" : // UP RIGHT
			bgDir = upRightBtnDOWN;
			break;
		case "downright" :	// DOWN RIGHT
			bgDir = downRightBtnDOWN;
			break;
		case "downleft" :	// DOWN LEFT
			bgDir = downLeftBtnDOWN;
			break;
		default :
			bgDir = defaultBG;
		}
		
		setChanged();
		notifyObservers("BGDIRECTION");
	}
	public void setTourelleOrientation( String orient ){
		switch( orient ){
		case "up" : // UP
			bgTour = upBtnDOWN;
			break;
		case "down" :	// DOWN
			bgTour = downBtnDOWN;
			break;
		case "left" : // LEFT
			bgTour = leftBtnDOWN;
			break;
		case "right" :	// RIGHT
			bgTour = rightBtnDOWN;
			break;
		case "center" :	// CENTER
			bgTour = centerBtnDOWN;
			break;
		case "upleft" : // UP LEFT
			bgTour = upLeftBtnDOWN;
			break;
		case "upright" : // UP RIGHT
			bgTour = upRightBtnDOWN;
			break;
		case "downright" :	// DOWN RIGHT
			bgTour = downRightBtnDOWN;
			break;
		case "downleft" :	// DOWN LEFT
			bgTour = downLeftBtnDOWN;
			break;
		default :
			bgTour = defaultBG;
		}

		
		setChanged();
		notifyObservers("BGTOURELLE");

	}
	
	public void setDirectionOver( String orient ){
		switch(orient){
		case "up" :
			bgDir = upBtnOVER;
			break;
		case "down" : 
			bgDir = downBtnOVER;
			break;
		case "left" : 
			bgDir = leftBtnOVER;
			break;
		case "right" : 
			bgDir = rightBtnOVER;
			break;
		case "center" : 
			bgDir = centerBtnOVER;
			break;
		case "upright" : 
			bgDir = upRightBtnOVER;
			break;
		case "upleft" : 
			bgDir = upLeftBtnOVER;
			break;
		case "downright" : 
			bgDir = downRightBtnOVER;
			break;
		case "downleft" : 
			bgDir = downLeftBtnOVER;
			break;
		default :
			bgDir = defaultBG;
		}
		
		setChanged();
		notifyObservers("BGDIRECTION");
	}
	public void setTourelleOver( String orient ){
		switch(orient){
		case "up" :
			bgTour = upBtnOVER;
			break;
		case "down" : 
			bgTour = downBtnOVER;
			break;
		case "left" : 
			bgTour = leftBtnOVER;
			break;
		case "right" : 
			bgTour = rightBtnOVER;
			break;
		case "center" : 
			bgTour = centerBtnOVER;
			break;
		case "upright" : 
			bgTour = upRightBtnOVER;
			break;
		case "upleft" : 
			bgTour = upLeftBtnOVER;
			break;
		case "downright" : 
			bgTour = downRightBtnOVER;
			break;
		case "downleft" : 
			bgTour = downLeftBtnOVER;
			break;
		default :
			bgTour = defaultBG;
		}
		
		setChanged();
		notifyObservers("BGTOURELLE");
	}

}


