package fr.gtm.bovoyages.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Tables;

import lombok.Data;

@Entity
@Table(name = "destinations")
@NamedQuery(name= "getAllDestinations", query = "SELECT d FROM Destination d")
@NamedQuery(name="getAllRegions", query = "SELECT d.region FROM Destination d")

@Data
public class Destination implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="pk_destination")
	private long id;
	private String region;
	private String description;
	@Column (name="deleted")
	private int deleted ;
	
	@OneToMany (cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_destination")
	private List<DatesVoyage> datesVoyage = new ArrayList<>();
//	@ElementCollection (fetch=FetchType.EAGER)
//	@CollectionTable (name="images",joinColumns= @JoinColumn(name="fk_destination"))
//	@Column(name="image")
//	private List<String> imagesString = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.LAZY)
	@JoinColumn(name="fk_destination")
	private List<Image> images=new ArrayList<Image>();

	public Destination (String region, String description) {
		this.region = region;
		this.description = description;
	}
	public Destination() {
		
	}
	
}


