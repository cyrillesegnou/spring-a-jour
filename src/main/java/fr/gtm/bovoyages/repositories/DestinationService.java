package fr.gtm.bovoyages.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.gtm.bovoyages.entities.Destination;

public interface DestinationService extends JpaRepository<Destination, Long> {
	
	
	List<Destination> getByRegion(String region);
}
