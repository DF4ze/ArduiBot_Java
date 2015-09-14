package modeles;

import java.util.Observable;
import java.util.Observer;

import modeles.catalogues.CtrlCat;
import modeles.catalogues.PilotCat;

public class DroneActions extends Observable implements Observer{
	
	// Instance
	private static DroneActions me = null;
	
	////// WAYZ
	public final static String upWay 		= "up";
	public final static String leftWay 		= "left";
	public final static String rightWay 	= "right";
	public final static String downWay 		= "down";

	public final static String uprightWay 	= "upright";
	public final static String upleftWay 	= "upleft";
	
	public final static String downrightWay = "downright";
	public final static String downleftWay 	= "downleft";
	
	public final static String centerWay 	= "center";
	
	////// POWER / Sliders
	public final static Integer maxVerticalPower 	= 255;
	public final static Integer minVerticalPower 	= 0;
	public final static Integer maxHorizontalPower 	= 255;
	public final static Integer minHorizontalPower 	= 0;
	
	////// BINDED KEY
	//// Direction
	// code
	public final static int dirUpCode 		= 90;		// Z
	public final static int dirLeftCode 	= 81;		// Q
	public final static int dirRightCode 	= 68;		// D
	public final static int dirDownCode 	= 83;		// S

	//// Tourelle
	// code
	public final static int tourUpCode 		= 38;		// fleche haut
	public final static int tourLeftCode 	= 37;		// gauche
	public final static int tourRightCode 	= 39;		// ...
	public final static int tourDownCode 	= 40;		// ...
	
	
	///////Axes
	//// Relatif ou absolut
	public final static boolean absoluteDirX = false;		
	public final static boolean absoluteDirY = true;
	public final static boolean absoluteTourX = false;
	public final static boolean absoluteTourY = false;
	
	
	////// Emplacement réseau du bot
	public final static String name = "ArduiBot";
	public final static String ip = "192.168.1.33";
//	public final static String ip = "127.0.0.1";
	public final static int port = 2009;

	
	///// Positions du Drone
	public final static int tourXCenter = 90;
	public final static int tourYCenter = 90;
	public final static int tourXMin = 0;
	public final static int tourYMin = 0;
	public final static int tourXMax = 180;
	public final static int tourYMax = 180;
	// direction
	private static int dirX = 0;
	private static int dirY = 0;
	private static double tourX = tourXCenter;
	private static double tourY = tourYCenter;
	
	private static int maxDegreeAtOnce = 2;
	
	///// Lumières du Drone
	private static boolean light = false;
	private static boolean lazer = false;
	private static boolean strob = false;
	
	//// Extras
	private static boolean standBy = false;
	private static boolean webCamService = false;
	
	private DroneActions() {
		
	}

	public static DroneActions getInstance(){
		if( me == null ){
			me = new DroneActions();
		}
		return me;
	}
	
	public static void setObserv( Observable obs ){
		obs.addObserver(getInstance());
	}

	@Override
	public void update(Observable model, Object messageObj) {
		
		if( messageObj instanceof String ){
			String message = (String)messageObj;
			
			
			if( model instanceof PilotCat ){
				PilotCat oModPilot = (PilotCat) model;
				if( message.equals("BGDIRECTION") ){
					if( oModPilot.isDirMove() ){
						int speed = 0;
						int delta = 0;
						
						switch(oModPilot.getDirWay()){
						case DroneActions.upWay :
							speed = oModPilot.getVertSliderDirPos();
							break;
						case DroneActions.downWay : 
							speed = -oModPilot.getVertSliderDirPos();
							break;
						case DroneActions.leftWay : 
							delta = -oModPilot.getHoriSliderDirPos();
							break;
						case DroneActions.rightWay : 
							delta = oModPilot.getHoriSliderDirPos();
							break;
						case DroneActions.centerWay : 
							speed = 0;
							delta = 0;
							break;
						case DroneActions.uprightWay : 
							speed = oModPilot.getVertSliderDirPos();
							delta = oModPilot.getHoriSliderDirPos();
							break;
						case DroneActions.upleftWay : 
							speed = oModPilot.getVertSliderDirPos();
							delta = -oModPilot.getHoriSliderDirPos();
							break;
						case DroneActions.downrightWay : 
							speed = -oModPilot.getVertSliderDirPos();
							delta = oModPilot.getHoriSliderDirPos();
							break;
						case DroneActions.downleftWay : 
							speed = oModPilot.getVertSliderDirPos();
							delta = -oModPilot.getHoriSliderDirPos();
							break;
						default :
							speed = 0;
							delta = 0;
						}
						DroneActions.setDirX(delta);
						DroneActions.setDirY(speed);
						
						setChanged();
						notifyObservers("DIRECTION");
					}else{
						DroneActions.setDirX(0);
						DroneActions.setDirY(0);
						
						setChanged();
						notifyObservers("DIRECTION");
					}
					
				
				}else if( message.equals("BGTOURELLE") ){
					if( oModPilot.isTourMove() ){
					
						switch(oModPilot.getTourWay()){
						case DroneActions.upWay :
							if( DroneActions.absoluteTourY ){ // critere a prendre en compte a chaque condition...
								
							}else{
								setTourY(getTourY() + sliderYToDegree( oModPilot.getVertSliderTourPos() ));
							}
							break;
						case DroneActions.downWay : 
							setTourY(getTourY() - sliderYToDegree( oModPilot.getVertSliderTourPos() ));
							break;
						case DroneActions.leftWay : 
							setTourX(getTourX() - sliderXToDegree( oModPilot.getHoriSliderTourPos() ));
							break;
						case DroneActions.rightWay : 
							setTourX(getTourX() + sliderXToDegree( oModPilot.getHoriSliderTourPos() ));
							break;
						case DroneActions.centerWay : 
							break;
						case DroneActions.uprightWay : 
							setTourX(getTourX() + sliderXToDegree( oModPilot.getHoriSliderTourPos() ));
							setTourY(getTourY() + sliderYToDegree( oModPilot.getVertSliderTourPos() ));
							break;
						case DroneActions.upleftWay : 
							setTourX(getTourX() - sliderXToDegree( oModPilot.getHoriSliderTourPos() ));
							setTourY(getTourY() + sliderYToDegree( oModPilot.getVertSliderTourPos() ));
							break;
						case DroneActions.downrightWay : 
							setTourX(getTourX() + sliderXToDegree( oModPilot.getHoriSliderTourPos() ));
							setTourY(getTourY() - sliderYToDegree( oModPilot.getVertSliderTourPos() ));
							break;
						case DroneActions.downleftWay : 
							setTourX(getTourX() - sliderXToDegree( oModPilot.getHoriSliderTourPos() ));
							setTourY(getTourY() - sliderYToDegree( oModPilot.getVertSliderTourPos() ));
							break;
						default :
						}
						
						if( getTourX() > tourXMax )
							setTourX(tourXMax);
						else if( getTourX() < tourXMin )
							setTourX(tourXMin);
						if( getTourY() > tourYMax )
							setTourY(tourYMax);
						else if( getTourY() < tourYMin )
							setTourY(tourYMin);
						
						me.setChanged();
						me.notifyObservers("TOURELLE");
	
					}
				}
				
			}else if( model instanceof CtrlCat ){
				CtrlCat oModCtrl = (CtrlCat)model;
				if( message.equals("STROBCHECK") ){
					setStrob(oModCtrl.isStrobCheck());
					me.setChanged();
					me.notifyObservers("STROBCHECK");
	
				}else if( message.equals("LIGHTCHECK") ){
					setLight(oModCtrl.isLightCheck());
					me.setChanged();
					me.notifyObservers("LIGHTCHECK");
					
				}else if( message.equals("LAZERCHECK") ){
					setLazer(oModCtrl.isLazerCheck());
					me.setChanged();
					me.notifyObservers("LAZERCHECK");
					
				}else if( message.equals("STANDBYCHECK") ){
					setStandBy(oModCtrl.isStandByCheck());
					me.setChanged();
					me.notifyObservers("STANDBYCHECK");
					
				}else if( message.equals("WEBCAMSERVICE") ){
					setWebCamService(oModCtrl.isWebCamService());
					me.setChanged();
					me.notifyObservers("WEBCAMSERVICE");
				} 
			}
		}
	}

	public static int getDirX() {
		return dirX;
	}
	private static void setDirX(int dirX) {
		DroneActions.dirX = dirX;
	}

	public static int getDirY() {
		return dirY;
	}
	private static void setDirY(int dirY) {
		DroneActions.dirY = dirY;
	}

	public static double getTourX() {
		return tourX;
	}
	private static void setTourX(double tourX) {
		DroneActions.tourX = tourX;
	}

	public static double getTourY() {
		return tourY;
	}
	private static void setTourY(double tourY) {
		DroneActions.tourY = tourY;
	}

	private static double sliderXToDegree( int slider ){
		return (double)slider*maxDegreeAtOnce/maxHorizontalPower;
	}
	private static double sliderYToDegree( int slider ){
		return (double)slider*maxDegreeAtOnce/maxVerticalPower;
	}

	public static boolean isLight() {
		return light;
	}
	private static void setLight(boolean light) {
		DroneActions.light = light;
	}

	public static boolean isLazer() {
		return lazer;
	}
	private static void setLazer(boolean lazer) {
		DroneActions.lazer = lazer;
	}

	public static boolean isStrob() {
		return strob;
	}
	private static void setStrob(boolean strobe) {
		DroneActions.strob = strobe;
	}

	public static boolean isStandBy() {
		return standBy;
	}
	public static void setStandBy(boolean standBy) {
		DroneActions.standBy = standBy;
	}

	public static boolean isWebCamService() {
		return webCamService;
	}
	public static void setWebCamService(boolean webCamService) {
		DroneActions.webCamService = webCamService;
	}
}
