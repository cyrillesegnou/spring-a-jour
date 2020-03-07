package fr.gtm.bovoyages.dto;

import java.util.Date;



import fr.gtm.bovoyages.entities.Voyageur;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class VoyageurDto {
	
	private long id;
	private String civilite;
	private String nom;
	private String prenom;

	private Date dateNaissance;

	public VoyageurDto(Voyageur v) {
		super();
		this.id = v.getId();
		this.civilite = v.getCivilite();
		this.nom = v.getNom();
		this.prenom = v.getPrenom();
		this.dateNaissance = v.getDateNaissance();
	}

	

}
