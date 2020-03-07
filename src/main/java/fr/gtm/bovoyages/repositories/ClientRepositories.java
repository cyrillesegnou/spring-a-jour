package fr.gtm.bovoyages.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import fr.gtm.bovoyages.entities.Client;

public interface ClientRepositories extends JpaRepository<Client, Long> {

//	@Query("SELECT c FROM Client c WHERE c.nom = ?1")
Client getByNom(String nom);
	
//	@Query(value = "SELECT sha from clients WHERE nom = ?1", nativeQuery = true)
//	String getValues(String nom);
	
	@Modifying
	@Query(value = "INSERT INTO clients (pk_client, nom, digest, emails, user) VALUES (0, ?1, ?2, ?3, ?4)", nativeQuery = true)
	@Transactional
	void creationClient( String nom, String digest, String user, String mail);
	
	Optional<Client> findByUserAndDigest(String nom, String digets);


}
