package fr.gtm.bovoyages.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "voyages")
@Data

public class Voyage implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="pk_voyage")
	private long id;
	private String region;
	private String descriptif;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fk_client")
	private Client client;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fk_dates_voyage")
	private DatesVoyage datesVoyages;

	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="voyages_voyageurs",
	joinColumns = @JoinColumn(name="fk_voyage"),
	inverseJoinColumns = @JoinColumn(name="fk_voyageur"))
	private List<Voyageur> voyageurs=new ArrayList<Voyageur>();
	
	public Voyage() {}
	public Voyage(String region, Client client, DatesVoyage dateVoyage, List<Voyageur> voyageurs) {
		this.region = region;
		this.client = client;
		this.datesVoyages = dateVoyage;
		this.voyageurs = voyageurs;
	}
}
