package fr.gtm.bovoyages.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.entities.Image;
import lombok.Data;

@Data
public class DestinationDto implements Serializable{
	
	private long id;
	private String region;
	private String description;
	private int deleted ;
	private List<DatesVoyage> datesVoyage = new ArrayList<>();
	private List<Image> images = new ArrayList<>();
	
	
	
	public DestinationDto(Destination d) {
		this.id= d.getId();
		this.region = d.getRegion(); 
		this.description = d.getDescription();
		this.deleted = d.getDeleted();
		this.datesVoyage = d.getDatesVoyage();
		this.images = d.getImages();
	}



	public DestinationDto() {
		super();
	}

}
