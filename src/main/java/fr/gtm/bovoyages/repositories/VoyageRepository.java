package fr.gtm.bovoyages.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.gtm.bovoyages.entities.Client;
import fr.gtm.bovoyages.entities.Voyage;

public interface VoyageRepository extends JpaRepository<Voyage, Long>{
	

List<Voyage> findByClient(Client c);

}
