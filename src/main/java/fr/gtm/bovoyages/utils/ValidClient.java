package fr.gtm.bovoyages.utils;

import java.io.Serializable;

import lombok.Data;

@Data
public class ValidClient implements Serializable{
	
	String nom;
	String user;
	
	boolean state;
	public ValidClient(String nom, String user, boolean state) {
		this.nom = nom;
		this.user =user ;
		this.state = state;	
}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
}
