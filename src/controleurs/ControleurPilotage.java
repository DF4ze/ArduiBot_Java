package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;

import modeles.GraphCat;
import vues.CamFrame;

public class ControleurPilotage implements MouseListener {

	private CamFrame cfFrame;
	@SuppressWarnings("unused")
	private GraphCat oModGraph;
	private HashMap<String, JButton>  DirectionBtn;
	private HashMap<String, JButton>  TourelleBtn;
	
	public ControleurPilotage( CamFrame cfFrame, GraphCat oModGraph) {
		this.cfFrame = cfFrame;
		this.oModGraph = oModGraph;
		
		DirectionBtn = this.cfFrame.getDirectionBtn();
		TourelleBtn = this.cfFrame.getTourelleBtn();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		boolean bDir = false;
		boolean bTour = false;
		String sDirection = null;
		
		for(Entry<String, JButton> entry : DirectionBtn.entrySet() ) {
		    String sDir = entry.getKey();
		    JButton button = entry.getValue();

		    if( button.equals(e.getSource()) ){
		    	bDir = true;
		    	sDirection = sDir;
		    	break;
		    }
		}
		if( !bDir )
		for(Entry<String, JButton> entry : TourelleBtn.entrySet() ) {
			String sDir = entry.getKey();
			JButton button = entry.getValue();
			
			if( button.equals(e.getSource()) ){
				bTour = true;
		    	sDirection = sDir;
				break;
			}
		}
		
		if( Debug.isEnable() )
			System.out.println("bouton appuyé "+((bDir)?"Direction":((bTour)?"Tour":"??"))+" vers "+sDirection);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		boolean bDir = false;
		boolean bTour = false;
		String sDirection = null;
		
		for(Entry<String, JButton> entry : DirectionBtn.entrySet() ) {
		    String sDir = entry.getKey();
		    JButton button = entry.getValue();

		    if( button.equals(e.getSource()) ){
		    	bDir = true;
		    	sDirection = sDir;
		    	break;
		    }
		}
		if( !bDir )
		for(Entry<String, JButton> entry : TourelleBtn.entrySet() ) {
			String sDir = entry.getKey();
			JButton button = entry.getValue();
			
			if( button.equals(e.getSource()) ){
				bTour = true;
		    	sDirection = sDir;
				break;
			}
		}
		
		if( Debug.isEnable() )
			System.out.println("bouton laché "+((bDir)?"Direction":((bTour)?"Tour":"??"))+" vers "+sDirection);
	}

}
