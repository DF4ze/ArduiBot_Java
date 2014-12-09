package controleurs.socketclient.com;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import modeles.dao.communication.FifoSenderAction;
import modeles.dao.communication.beansactions.IAction;


public class Emission implements Runnable {

	private ObjectOutputStream out;
	private static LinkedList<IAction> fifo = FifoSenderAction.getInstance();
	private boolean bRunning = true;
	private static IAction lastActionHero = null;
	
	public Emission(ObjectOutputStream out) {
		this.out = out;
	}


	
	public static void addAction( IAction action ){
		boolean send = true;
		if( lastActionHero != null ){
			if( lastActionHero.toString().equals(action.toString()) ){
				send = false;
			}
				
		}else
			lastActionHero = action;
		
		if( send ){
			synchronized (fifo) {
				fifo.addLast(action);
				fifo.notifyAll();
			}
			System.out.println("Send : Last : "+lastActionHero.toString()+" new : "+action.toString());
			lastActionHero = action;
		}else
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

	
	public void stop(){
		bRunning = false;
		synchronized (fifo) {
			fifo.clear();
			fifo.notifyAll();
		}

	}

	public void run() {
		
		while( bRunning ){	
			
			if( fifo.size() == 0  )
				synchronized (fifo) {
					try {
						fifo.wait();
					} catch (InterruptedException e) {}
				}
			
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
	}
}