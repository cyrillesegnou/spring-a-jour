package fr.gtm.bovoyages.entities;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name ="dates_voyages")
@Access(AccessType.FIELD)
@Data
public class DatesVoyage implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="pk_date_voyage")
	private long id;
	@Column (name="date_depart")
	private Date dateAller;
	@Column (name="date_retour")
	private Date dateRetour;
	@Column (name="prixHT")
	private double prixHT;
	@Column (name="nb_places")
	private int nbPlaces;
	@Column (name = "promotion")
	private int promotion ;
	@Column (name = "deleted")
	private int deleted ;
	
	
	public DatesVoyage() {
	
	}

	public DatesVoyage(Date dateDepart, Date dateRetour, double prixHT, int nbPlaces) {

		this.dateAller = dateDepart;
		this.dateRetour = dateRetour;
		this.prixHT = prixHT;
		this.nbPlaces = nbPlaces;
	}

}
