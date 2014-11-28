package modeles.dao.communication;

import java.util.LinkedList;

import modeles.dao.communication.beanssend.IAction;

public class FifoSenderAction {
	private static LinkedList<IAction> fifo = null;

	private FifoSenderAction() {
		
	}
	public static LinkedList<IAction> getInstance(){
		if( fifo == null ){
			fifo = new LinkedList<IAction>();
		}
		return fifo;
	}
}
