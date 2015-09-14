package vues.campanels.bottom;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modeles.catalogues.CtrlCat;

public class DistancePanel extends JPanel implements Observer{


	private static final long serialVersionUID = 1L;
	
	private ArrayList<JPanel> distsP = new ArrayList<JPanel>();
	private HashMap<Integer, Integer> distances = new HashMap<Integer, Integer>();
	private JLabel lbl = new JLabel("Affichage de l'utrason");
	

	public DistancePanel(CtrlCat oModel) {
		oModel.addObserver(this);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setMinimumSize(new Dimension(0, 20));
		setPreferredSize(new Dimension(0, 20));
	}


	public void setDistances( HashMap<Integer, Integer> distances ){
		

	}


	@Override
	public void update(Observable o, Object arg) {
				
		if( arg instanceof HashMap ){
			
			try{
				@SuppressWarnings("unchecked")
				HashMap<Integer, Integer> distTemp = (HashMap<Integer, Integer>)arg;
				
				for( Entry<Integer, Integer> distSet : distTemp.entrySet() ){
					distances.put(distSet.getKey(), distSet.getValue());
				}
				
				
				//System.out.println( "nb distances : "+distances.size()+"............." );
				
				if( distsP.size() != 0 )
					for( JPanel pan : distsP )
						remove(pan);
				
				SortedSet<Integer> keys = new TreeSet<Integer>(distances.keySet());
				for( Integer key : keys ){
					Integer dist = distances.get(key);
					
					JPanel pan = new JPanel();
					if( dist < 40 )
						pan.setBackground(Color.RED);
					else if( dist < 90 )
						pan.setBackground(Color.ORANGE);
					else
						pan.setBackground(Color.GREEN);
					
					JLabel lbl = new JLabel( key.toString()+"° "+dist.toString()+"cm" );
					pan.add(lbl);
					
					pan.setPreferredSize(this.getSize());
					//pan.setMinimumSize(getMaximumSize());
					
					add( pan );
					distsP.add(pan);
				}
				
				//invalidate();
				revalidate();
				repaint();
				
			}catch( Exception e ){}
		}
	}
}
