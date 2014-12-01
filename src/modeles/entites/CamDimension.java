package modeles.entites;

import java.awt.Dimension;

public class CamDimension extends Dimension {

	private static final long serialVersionUID = 1L;

	private String name;
	
	public CamDimension() {
	}

	public CamDimension(Dimension d) {
		super(d);
	}

	public CamDimension(int width, int height) {
		super(width, height);
	}
	
	public CamDimension(int width, int height, String name) {
		super(width, height);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return width+"x"+height+"("+name+")";
	}

}
