package fr.gtm.bovoyages.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class Image {
	@Id
	@Column(name = "image")
	private String image;
	@Column(name = "fk_destination")
	private long fkDestination;
	
}