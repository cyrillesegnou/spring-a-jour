package fr.gtm.bovoyages.dto;

import java.io.Serializable;
import java.util.Date;

import fr.gtm.bovoyages.entities.DatesVoyage;
import lombok.Data;

@Data
public class DatesVoyageDto implements Serializable{
	
	private long id;
	private Date dateAller;
	private Date dateRetour;
	private double prixHT;
	private int nbPlaces;
	private int promotion ;
	private int deleted ;
	
	
	public DatesVoyageDto(DatesVoyage datesVoyages) {
	
		this.dateAller = datesVoyages.getDateAller();
		this.dateRetour = datesVoyages.getDateRetour();
		this.prixHT = datesVoyages.getPrixHT();
		this.nbPlaces = datesVoyages.getNbPlaces();
		this.promotion = datesVoyages.getNbPlaces();
		this.deleted = datesVoyages.getDeleted();
	}
	
	

}
