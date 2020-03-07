package fr.gtm.bovoyages.rest;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.gtm.bovoyages.dto.DatesVoyageDto;
import fr.gtm.bovoyages.dto.DestinationDto;
import fr.gtm.bovoyages.dto.VoyageDTO;
import fr.gtm.bovoyages.entities.Client;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.entities.Voyage;
import fr.gtm.bovoyages.entities.Voyageur;
import fr.gtm.bovoyages.repositories.ClientRepositories;
import fr.gtm.bovoyages.repositories.DatesVoyageService;
import fr.gtm.bovoyages.repositories.DestinationService;
import fr.gtm.bovoyages.repositories.VoyageRepository;
import fr.gtm.bovoyages.utils.InscriptionResponse;
import fr.gtm.bovoyages.utils.Util;
import fr.gtm.bovoyages.utils.ValidClient;

@RestController
@CrossOrigin

public class VoyageControleur {

	@Autowired
	private DatesVoyageService dvrepo;
	@Autowired
	private DestinationService drepo;
	@Autowired
	private VoyageRepository vrepo ;
	@Autowired
	private ClientRepositories crepo ;

	@GetMapping(path = "/alldestinations")
	public List<DestinationDto> findAllDestination() {

		List<Destination> destinations = drepo.findAll();
		List<DestinationDto> ddto = new ArrayList<>();
		for (Destination d : destinations) {
			ddto.add(new DestinationDto(d));
		}
		return ddto;
		
	}
	@GetMapping(path = "/allvoyages")
	public List<Voyage> findAllVoyages() {
		return vrepo.findAll() ;
	}

	@PostMapping("/voyage/commande")
	public String commanderVoyage(@RequestBody Voyage v) {
		long idDate = v.getDatesVoyages().getId();
		int nbPlaces = dvrepo.findById(idDate).get().getNbPlaces();
		int nbVoyageurs = v.getVoyageurs().size();
		if (nbPlaces < nbVoyageurs) {
			return "le nombre sélectionné est supérieur au nombre de place disponible";
		}
		else {
		v.getDatesVoyages().setNbPlaces(nbPlaces - nbVoyageurs);
		dvrepo.save(v.getDatesVoyages());
		vrepo.save(v);
		return "votre voyage à ete commander pour la region : " + 
		v.getRegion() + " pour les dates " + v.getDatesVoyages().getDateDepart() 
		+ " " + v.getDatesVoyages().getDateRetour();
	}
	
	}
	
	@PostMapping("/client/newclient")
	public String inscriptionc(String nom, String pswd, String mail, String user ) throws NoSuchAlgorithmException {
		String digest = Util.toSha256(pswd);
		try {
			Client c = crepo.getByNom(nom);
			c.getNom();
		} catch (NullPointerException e) {
			crepo.creationClient(nom, digest, mail, user); 
					return "votre compte à bien été créé";  
		}
		return "le nom de cette utilisatur existe deja, veuillez modifier et reéssayer";
	}
		
//		@PostMapping("/inscription/newclient")
//		public InscriptionResponse inscription(String nom, String pswd) throws NoSuchAlgorithmException {
//			String digest = Util.toSha256(pswd);
//			Optional<Client> opt = crepo.getByNom(nom);
//			if(opt.isPresent()) {
//				return new InscriptionResponse("cet utilisateur existe déjà ", false);
//			}
//			c.setDigest(Util.toSha256(c.getdigets()));
//			repo.save(newUser);
//			return new InscriptionResponse("inscription effectuée", true);
//			
//		}
	
	@GetMapping("/authentification/{mon}/{pswd}")
	public ValidClient authentification(@PathVariable String nom, @PathVariable String pswd) throws NoSuchAlgorithmException {
		Optional<Client> opt=crepo.findByNomAndDigest(nom, Util.toSha256(pswd));
		if(opt.isPresent()) {
			Client c = opt.get();
			return new ValidClient(c.getNom(), true);
		}
		return new ValidClient(",", false);
	}
	
	@GetMapping("/destinations")
	public List<DestinationDto> getAllDestinations() {
		List<Destination> destinations = drepo.findAll();
		List<DestinationDto> dtos = new ArrayList<DestinationDto>();
		for (Destination d : destinations) {
			if (d.getDeleted() != 1) {
				dtos.add(new DestinationDto(d));
			}
		}
		return dtos;
	}
	
	@GetMapping("/destinationDetails/{id}")
	public DestinationDto getDestinationDetails(@PathVariable("id") long id) {
		Destination destination = drepo.findById(id).get();
		if (destination.getDeleted() != 1) {
			return new DestinationDto(destination);
		} else {
			return new DestinationDto();
		}
	}
	
	@GetMapping("/destinationsByRegion/{region}")
	public List<DestinationDto> getDestinationByRegion(@PathVariable String region) {
		List<Destination> destinations = drepo.getByRegion(region);
		List<DestinationDto> dtos = new ArrayList<DestinationDto>();
		for (Destination d : destinations) {
			if (d.getDeleted() != 1) {
				dtos.add(new DestinationDto(d));
			}
		}
		return dtos;
	}
	
	@GetMapping("/destination/{id}/dates")
	public List<DatesVoyageDto> getDatesByDestinationId(@PathVariable("id") long id){
		Destination destination = drepo.findById(id).get();
		List<DatesVoyageDto> dtos=new ArrayList<DatesVoyageDto>();
		List<DatesVoyage> dates=destination.getDatesVoyage();
		for(DatesVoyage d:dates) {
			dtos.add(new DatesVoyageDto(d));
		}
		return dtos;
	}
	
		@PostMapping("/voyage/{id}/addVoyageurs")
		public Voyage addvoyageurs(@PathVariable("id") long id, @RequestBody Voyageur voyageur) {
			Voyage voyage = vrepo.findById(id).get();
			DatesVoyage dates=voyage.getDatesVoyages();
			dates.setNbPlaces(dates.getNbPlaces() - 1);
			dvrepo.save(dates);
			List<Voyageur> voyageurs = voyage.getVoyageurs();
			voyageurs.add(voyageur);
			voyage.setVoyageurs(voyageurs);
			return voyage;
		}
	
	}
//	@PostMapping("/mail/send")
//	@Async
//	public void sendMail(@RequestBody MailReceptor r) {
//		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(r.getEmail());
//		mail.setFrom("NePasRepondre@bovoyages.net");
//		mail.setSubject("Merci pour votre commande");
//		mail.setText("Très cher " + r.getPrenom()
//				+ ",\n\nNous vt pour votre commande.\nProfitez bien de votre séjour et à bientôt sur BoVoyages\n\nBovoyages.net");
//		mailSender.send(mail);
//
//	}
//	}
