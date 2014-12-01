package vues.campanels.controls;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import controleurs.Debug;
import modeles.catalogues.CtrlCat;

public class ExtraPanel extends JPanel implements Observer{


	private static final long serialVersionUID = 1L;
	
	private JCheckBox cbLight;
	private JCheckBox cbStrob;
	private JCheckBox cbLazer;
	
	private CtrlCat oModel;

	public ExtraPanel( CtrlCat oModel ) {
		this.oModel = oModel;
		oModel.addObserver(this);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createTitledBorder("Options"));
		
		cbLight = new JCheckBox("Light");
		cbStrob = new JCheckBox("Strob");
		cbLazer = new JCheckBox("Lazer");

		add(cbLight);
		add(cbStrob);
		add(cbLazer);	
		
		if( oModel.isExtraEnable() ){
			cbLight.setEnabled(true);
			cbStrob.setEnabled(true);
			cbLazer.setEnabled(true);
		}else{
			cbLight.setEnabled(false);
			cbStrob.setEnabled(false);
			cbLazer.setEnabled(false);
			
		}
			
		
	}
	public void setModele( CtrlCat oModel ){
		
	}
	@Override
	public void update(Observable o, Object message) {
		
		if( o == oModel && message.equals("EXTRAENABLE")){
			if( Debug.isEnable() )
				System.out.println("Extra Panel : Update Receved");

			cbLight.setSelected(oModel.isLightCheck());
			cbStrob.setSelected(oModel.isStrobCheck());
			cbLazer.setSelected(oModel.isLazerCheck());
			if( oModel.isExtraEnable() ){
				cbLight.setEnabled(true);
				cbStrob.setEnabled(true);
				cbLazer.setEnabled(true);
			}else{
				cbLight.setEnabled(false);
				cbStrob.setEnabled(false);
				cbLazer.setEnabled(false);
			}		
		}else if( message.equals("LIGHTCHECK") ){
			cbLight.setSelected(oModel.isLightCheck());
			
		}else if( message.equals("STROBCHECK") ){
			cbStrob.setSelected(oModel.isStrobCheck());
			
		}else if( message.equals("LAZERCHECK") ){
			cbLazer.setSelected(oModel.isLazerCheck());
		}
	}
	
	public void setListener( ActionListener ac){
		cbLight.addActionListener( ac );
		cbLight.setActionCommand("CBLIGHT");
		
		cbStrob.addActionListener( ac );
		cbStrob.setActionCommand("CBSTROB");
		
		cbLazer.addActionListener( ac );
		cbLazer.setActionCommand("CBLAZER");
	}
}
