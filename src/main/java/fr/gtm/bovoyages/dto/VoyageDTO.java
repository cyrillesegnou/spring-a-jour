package fr.gtm.bovoyages.dto;

import java.util.ArrayList;
import java.util.List;

import fr.gtm.bovoyages.entities.Client;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Voyage;
import fr.gtm.bovoyages.entities.Voyageur;
import lombok.Data;

@Data
public class VoyageDTO {
		
	private long id;
	private String region;
	private String descriptif;
	private Client client;
	private DatesVoyage datesVoyages;
	private List<Voyageur> voyageurs = new ArrayList<>();
	
	public VoyageDTO() {
		
	}
	
	public VoyageDTO(Voyage v) {
		super();
		this.region = v.getRegion();
		this.descriptif = v.getDescriptif();
		this.client = v.getClient();
		this.datesVoyages = v.getDatesVoyages();
		this.voyageurs = v.getVoyageurs();
	}
	}
