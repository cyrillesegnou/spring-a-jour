package fr.gtm.bovoyages.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.gtm.bovoyages.entities.DatesVoyage;

public interface DatesVoyageService extends JpaRepository<DatesVoyage, Long> {

}
