package modeles.catalogues;

import java.util.ArrayList;
import java.util.Observable;



import modeles.entites.Profile;


// Unused??????
public class ProfileCat extends Observable{
	
	private String name;
	private ArrayList<Profile> alProfiles;

	public ProfileCat() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<?> getAlProfiles() {
		return alProfiles;
	}

	public void setAlProfiles(ArrayList<Profile> alProfiles) {
		this.alProfiles = alProfiles;
	}
	
	public void addProfile( Profile pro ){
		alProfiles.add(pro);
	}
	
	public void delProfile( Profile pro ){
		alProfiles.remove(pro);
	}
	public void delProfile( int pro ){
		alProfiles.remove(pro);
	}
	
	public void clear(){
		alProfiles.clear();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
