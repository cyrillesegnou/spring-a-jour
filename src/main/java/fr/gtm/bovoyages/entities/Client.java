package fr.gtm.bovoyages.entities;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
@Entity
@Table(name = "clients")
@NamedQuery(name="getClientByNom", query = "SELECT c FROM Client c WHERE c.nom = :nom")
@Data
@Access(AccessType.FIELD)
public class Client implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="pk_client")
	private long id;
	
	private String nom;
	private String digest ;
	private String user;
	@Column (name="emails")
	private String mail;
	@Transient private String pswd ;
	
	public Client() {}
	
	public Client(String nom,String digest) {
	this.nom=nom;
	this.digest=digest ;
	
}
	
	
}