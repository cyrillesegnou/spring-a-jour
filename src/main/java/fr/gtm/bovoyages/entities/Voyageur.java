package fr.gtm.bovoyages.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "voyageurs")
@Data
public class Voyageur implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="pk_voyageur")
	private long id;
	private String civilite;
	private String nom;
	private String prenom;
	@Column (name="date_naissance")
	private Date dateNaissance;

	
	public Voyageur() {}
	
	public Voyageur(String civilite, String nom, String prenom, Date dateNaissance) {

		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

}










//@ManyToMany(mappedBy="voyageurs")
//private List<Voyage> voyages = new ArrayList<>();
