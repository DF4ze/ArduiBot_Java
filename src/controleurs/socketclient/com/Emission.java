package controleurs.socketclient.com;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.TimerTask;

import modeles.dao.communication.FifoSenderAction;
import modeles.dao.communication.beansactions.DirectionAction;
import modeles.dao.communication.beansactions.ExtraAction;
import modeles.dao.communication.beansactions.GetStateAction;
import modeles.dao.communication.beansactions.IAction;
import modeles.dao.communication.beansactions.TourelleAction;
import controleurs.Debug;


public class Emission extends TimerTask {

	private ObjectOutputStream out;
	private static LinkedList<IAction> fifo = FifoSenderAction.getInstance();
	//private boolean bRunning = true;
	private static IAction lastActionHero = null;
	
	public Emission(ObjectOutputStream out) {
		this.out = out;
	}


	
	public static void addAction( IAction action ){
		boolean send = true;
		if( lastActionHero != null ){
			if( lastActionHero.toString().equals(action.toString()) ){
				if( !action.isRepeatable() )
					send = false;
			}
				
		}else
			lastActionHero = action;
		
		if( send ){
			synchronized (fifo) {
				fifo.addLast(action);
				fifo.notifyAll();
			}
			if( Debug.isEnable() )
				System.out.println("Send : Last : "+lastActionHero.toString()+" new : ("+action.getClass().getName()+") "+action.toString());
			lastActionHero = action;
		}else
			if( Debug.isEnable() )
				System.out.println("Not Send : Last : "+lastActionHero.toString()+" new : "+action.toString());

		
	}
	
	protected IAction getAction(){
		IAction action = null;
		synchronized (fifo) {
			try{
				action = fifo.removeFirst();
			}catch(NoSuchElementException e){}
		}
		return action;
	}

	
//	public void stop(){
//		bRunning = false;
//		synchronized (fifo) {
//			fifo.clear();
//			fifo.notifyAll();
//		}
//
//	}

	public void run() {
		
//		while( bRunning ){	
			
//			if( fifo.size() == 0  )
//				synchronized (fifo) {
//					try {
//						fifo.wait();
//					} catch (InterruptedException e) {}
//				}
		
			if( fifo.size() != 0  ){
				manageFifo();
				IAction action;
				while( (action = getAction()) != null ){
					try {
						out.writeObject( action );
						out.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		//}
		
//		if( Debug.isEnable() )
//			System.out.println( "Sortie propre du thread Emission" );
	}
	
	private void manageFifo(){
		LinkedList<DirectionAction> llDir = new LinkedList<DirectionAction>();
		LinkedList<TourelleAction> llTour = new LinkedList<TourelleAction>();
		LinkedList<ExtraAction> llExtra = new LinkedList<ExtraAction>();
		LinkedList<GetStateAction> llState = new LinkedList<GetStateAction>();
		LinkedList<IAction> llOther = new LinkedList<IAction>();
		
		synchronized (fifo) {
			for( IAction ia : fifo ){
				if( ia instanceof DirectionAction ){
					llDir.addLast((DirectionAction)ia);
					
				}else if( ia instanceof TourelleAction ){
					llTour.addLast((TourelleAction)ia);
					
				}else if( ia instanceof ExtraAction ){
					llExtra.addLast((ExtraAction)ia);
					
				}else if( ia instanceof GetStateAction ){
					llState.addLast((GetStateAction)ia);
					
				}else{
					llOther.addLast(ia);
				}
			}
			fifo.clear();
			
			DirectionAction priorDa = manageDir(llDir);
			if( priorDa != null )
				fifo.addLast(priorDa);
			
			TourelleAction priorTa = manageTour(llTour);
			if( priorTa != null )
				fifo.addLast(priorTa);
			
			fifo.addAll(llExtra);
			fifo.addAll(llState);
			fifo.addAll(llOther);
				
		}
		
	}
	private DirectionAction manageDir( LinkedList<DirectionAction> llDir ){
		DirectionAction priorDa = null;
		if( llDir.size() != 0 ){
			int refVit = llDir.getFirst().getVitesse();
			int refDelta = llDir.getFirst().getDelta();
		
			int sumVit = 0;
			int sumDelta = 0;
			
			int minVit = refVit;
			int minDelta = refDelta;
			
			int maxVit = refVit;
			int maxDelta = refDelta;
			
			for( DirectionAction da : llDir){
				if( da.getPriority() == IAction.prioHighest ){
					priorDa = da;
					break;
				}else{
					int vit = da.getVitesse();
					int del = da.getDelta();
					
					sumVit += vit;
					sumDelta += del;
					
					if( vit > maxVit )
						maxVit = vit;
					else if( vit < minVit )
						minVit = vit;
					
					if( del > maxDelta )
						maxDelta = del;
					else if( del < minDelta )
						minDelta = del;	
				}
			}
			if( priorDa == null ){
				int moyVit = sumVit/llDir.size();
				int moyDel = sumDelta/llDir.size();
				
				priorDa = new DirectionAction();
				if( moyVit > refVit )
					priorDa.setVitesse(maxVit);
				else
					priorDa.setVitesse(minVit);
				
				if( moyDel > refDelta )
					priorDa.setDelta(maxDelta);
				else
					priorDa.setDelta(minDelta);
			}
		}
		return priorDa;
	}	
	
	private TourelleAction manageTour( LinkedList<TourelleAction> llTour ){
		TourelleAction priorTa = null;
		if( llTour.size() != 0 ){
			int refX = llTour.getFirst().getDegresX();
			int refY = llTour.getFirst().getDegresY();
		
			int sumX = 0;
			int sumY = 0;
			
			int minX = refX;
			int minY = refY;
			
			int maxX = refX;
			int maxY = refY;
			
			for( TourelleAction ta : llTour){
				if( ta.getPriority() == IAction.prioHighest ){
					priorTa = ta;
					break;
				}else{
					int x = ta.getDegresX();
					int y = ta.getDegresY();
					
					sumX += x;
					sumY += y;
					
					if( x > maxX )
						maxX = x;
					else if( x < minX )
						minX = x;
					
					if( y > maxY )
						maxY = y;
					else if( y < minY )
						minY = y;	
				}
			}
			if( priorTa == null ){
				int moyX = sumX/llTour.size();
				int moyY = sumY/llTour.size();
				
				priorTa = new TourelleAction();
				if( moyX > refX )
					priorTa.setDegresX(maxX);
				else
					priorTa.setDegresX(minX);
				
				if( moyY > refY )
					priorTa.setDegresY(maxY);
				else
					priorTa.setDegresY(minY);
			}
		}
		return priorTa;
	}
}