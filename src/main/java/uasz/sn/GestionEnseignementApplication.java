package uasz.sn;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uasz.sn.authentification.modeles.Role;
import uasz.sn.authentification.modeles.Utilisateur;
import uasz.sn.authentification.repositories.UtilisateurRepository;
import uasz.sn.authentification.services.UtilisateurService;
import uasz.sn.utilisateur.modeles.Permanent;
import uasz.sn.utilisateur.modeles.Vacataire;
import uasz.sn.utilisateur.services.EnseignantService;

@SpringBootApplication
public class GestionEnseignementApplication implements CommandLineRunner{
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private EnseignantService enseignantService;

	public static void main(String[] args) {
		SpringApplication.run(GestionEnseignementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Role permanent = utilisateurService.ajouter_Role(new Role("Permanent"));
		Role vacataire = utilisateurService.ajouter_Role(new Role("Vacataire"));

		Permanent user1 = new Permanent();
		user1.setNom("Diop");
		user1.setPrenom("Ibrahima");
		user1.setUsername("iDiop@uasz.sn");
		// user1.setPassword("diop123");
		user1.setPassword("$2a$10$Dxk24yQ4XIQEIzIjd7Qdkuhsbql0ixT7BV.Pb1Kvze6qfVwWc9Dje");
		user1.setSpecialite("Web Semantique");
		user1.setMatricule("ID2024");
		user1.setDateCreation(new Date());
		user1.setActive(true);
		user1.setGrade("Professeur");
		enseignantService.ajouter(user1);
		utilisateurService.ajouter_UtilisateurRoles(user1, permanent);

		Vacataire user2 = new Vacataire();
		user2.setNom("Malack");
		user2.setPrenom("Camir");
		user2.setUsername("cmalack@uasz.sn");
		// user2.setPassword("malack123");
		user2.setPassword("$2a$10$h8tzL5WxIpPbkUFaWKA5u.OwzVYCDNdvWtOiK0amBs.62UVCVijVq");
		user2.setSpecialite("Web Semantique");
		user2.setDateCreation(new Date());
		user2.setActive(true);
		enseignantService.ajouter(user2);
		utilisateurService.ajouter_UtilisateurRoles(user2, vacataire);
	}

}
