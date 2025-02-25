package uasz.sn;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uasz.sn.authentification.modeles.Role;
import uasz.sn.authentification.services.UtilisateurService;
import uasz.sn.maquette.modeles.Classe;
import uasz.sn.maquette.modeles.Formation;
import uasz.sn.maquette.modeles.Maquette;
import uasz.sn.maquette.services.ClasseService;
import uasz.sn.maquette.services.FormationService;
import uasz.sn.maquette.services.MaquetteService;
import uasz.sn.repartition.modeles.Choix;
import uasz.sn.repartition.modeles.Enseignement;
import uasz.sn.repartition.services.ChoixService;
import uasz.sn.repartition.services.EnseignementService;
import uasz.sn.utilisateur.modeles.Etudiant;
import uasz.sn.utilisateur.modeles.Permanent;
import uasz.sn.utilisateur.modeles.Vacataire;
import uasz.sn.utilisateur.services.EnseignantService;
import uasz.sn.utilisateur.services.EtudiantService;
import uasz.sn.syllabus.modeles.Ec;
import uasz.sn.syllabus.modeles.Ue;
import uasz.sn.syllabus.services.EcService;
import uasz.sn.syllabus.services.UeService;

@SpringBootApplication
public class GestionEnseignementApplication implements CommandLineRunner {
	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private EnseignantService enseignantService;

	@Autowired
	private EtudiantService etudiantService;

	@Autowired
	private UeService ueService;

	@Autowired
	private EcService ecService;

	@Autowired
	private FormationService formationService ;

	@Autowired
	private ClasseService classeService ;

	@Autowired
	private MaquetteService maquetteService ;

	@Autowired
	private ChoixService choixService ;

	@Autowired
	private EnseignementService enseignementService ;

	public static void main(String[] args) {
		SpringApplication.run(GestionEnseignementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role permanent = utilisateurService.ajouter_Role(new Role("Permanent"));
		Role vacataire = utilisateurService.ajouter_Role(new Role("Vacataire"));
		Role etudiant = utilisateurService.ajouter_Role(new Role("Etudiant"));
		Role chefDepartement = utilisateurService.ajouter_Role(new Role("ChefDepartement"));

		Etudiant etu = new Etudiant();
		etu.setNom("Sarr");
		etu.setPrenom("Pape Dethie");
		etu.setUsername("dethie@uasz.sn");
		// etu.setPassword("sarr123");
		etu.setPassword("$2a$10$M/UpYcc84JebqPtUVKVEe.85zZN8UcCizmk9AabTJMPbArzcVnbX.");
		etu.setMatricule("PS2024");
		etudiantService.ajouter(etu);
		utilisateurService.ajouter_UtilisateurRoles(etu, etudiant);

		Permanent user1 = new Permanent();
		user1.setNom("Diagne");
		user1.setPrenom("Serigne");
		user1.setUsername("sDiagne@uasz.sn");
		// user1.setPassword("diagne123");
		user1.setPassword("$2a$10$CiAbXnZ1HC/J4awmpuiUiuCyaVEAGnPML91WHlxoMDDNP2491/4Qi");
		user1.setSpecialite("Web Semantique");
		user1.setMatricule("SD2024");
		user1.setDateCreation(new Date());
		user1.setActive(true);
		user1.setGrade("Professeur");
		enseignantService.ajouter(user1);
		utilisateurService.ajouter_UtilisateurRoles(user1, chefDepartement);

		Permanent user2 = new Permanent();
		user2.setNom("Diop");
		user2.setPrenom("Ibrahima");
		user2.setUsername("iDiop@uasz.sn");
		// user2.setPassword("diop123");
		user2.setPassword("$2a$10$Dxk24yQ4XIQEIzIjd7Qdkuhsbql0ixT7BV.Pb1Kvze6qfVwWc9Dje");
		user2.setSpecialite("Web Semantique");
		user2.setMatricule("ID2024");
		user2.setDateCreation(new Date());
		user2.setActive(true);
		user2.setGrade("Professeur");
		enseignantService.ajouter(user2);
		utilisateurService.ajouter_UtilisateurRoles(user2, permanent);

		Vacataire user3 = new Vacataire();
		user3.setNom("Malack");
		user3.setPrenom("Camir");
		user3.setUsername("cmalack@uasz.sn");
		// user2.setPassword("malack123");
		user3.setPassword("$2a$10$h8tzL5WxIpPbkUFaWKA5u.OwzVYCDNdvWtOiK0amBs.62UVCVijVq");
		user3.setSpecialite("Web Semantique");
		user3.setDateCreation(new Date());
		user3.setActive(true);
		enseignantService.ajouter(user3);
		utilisateurService.ajouter_UtilisateurRoles(user3, vacataire);

		Ue ue1 = new Ue(null,"Reseaux Et Systemes", "INF360", 0, 0, "reseaux et systemes", null, null, null, null);
		Ue ue2 = new Ue(null,"Genie Logiciel 2", "INF361", 0, 0, "genie logiciel 2", null, null, null, null);
		Ue ue3 = new Ue(null,"Humanite et Entreprise", "INF362", 0, 0, "humanite et entreprise", null, null, null, null);
		Ue ue4 = new Ue(null,"Projet Tutore", "INF363", 0, 0, "projet tutore", null, null, null, null);
		Ue ue5 = new Ue(null,"Technologies Avances", "INF364", 0, 0, "Les technologies avances", null, null, null, null);
		ueService.ajouter(ue1);
		ueService.ajouter(ue2);
		ueService.ajouter(ue3);
		ueService.ajouter(ue4);
		ueService.ajouter(ue5);

		Ec ec1 = new Ec();
		ec1.setLibelle("Administration Reseau");
		ec1.setCode("INF3631");
		ec1.setCm(20);
		ec1.setDescription("Description...");
		ec1.setUe(ue1);
		Ec ec2 = new Ec();
		ec2.setLibelle("Securite des Reseaux");
		ec2.setCode("INF3632");
		ec2.setCm(30);
		ec2.setDescription("Description...");
		ec2.setUe(ue1);
		ecService.ajouterEc(ec1.getUe().getId(), ec1);
		ecService.ajouterEc(ec2.getUe().getId(), ec2);

		Formation formation1 = new Formation();
		formation1.setLibelle("L2I");
		formation1.setDescription("Licence en Ingenierie Informatique");
		formation1.setDateCration(new Date());
		Formation formation2 = new Formation();
		formation2.setLibelle("MIO");
		formation2.setDescription("Management Informatique");
		formation2.setDateCration(new Date());
		formationService.ajouter(formation1);
		formationService.ajouter(formation2);

		Classe classe1 = new Classe(); 
		classe1.setLibelle("L12I");
		classe1.setDescription("Description...");
		classe1.setFormation(formation1);
		Classe classe2 = new Classe(); 
		classe2.setLibelle("L22I");
		classe2.setDescription("Description...");
		classe2.setFormation(formation1);
		classeService.ajouterClasse(formation1.getId(), classe1);
		classeService.ajouterClasse(formation1.getId(), classe2);

		Maquette maquette1 = new Maquette();
		maquette1.setUes(List.of(ue1, ue2, ue3));
		maquette1.setSemestre("semestre1");
		maquette1.setClasse(classe1);
		Maquette maquette2 = new Maquette();
		maquette2.setUes(List.of(ue3, ue4, ue5));
		maquette2.setSemestre("semestre2");
		maquette2.setClasse(classe1);
		maquetteService.ajouterMaquette(classe1.getId(), maquette1);
		maquetteService.ajouterMaquette(classe1.getId(), maquette2);

		Enseignement ens1 = new Enseignement();
		ens1.setType("CM");
		ens1.setEc(ec2);
		// ens.setChoix(List.of(choix1, choix2));
		enseignementService.ajouter(ens1);

		Enseignement ens2 = new Enseignement();
		ens2.setType("TD");
		ens2.setEc(ec2);
		// ens.setChoix(List.of(choix1, choix2));
		enseignementService.ajouter(ens1);
		enseignementService.ajouter(ens2);

		// Choix choix1 = new Choix();
		// choix1.setEnseignant(user2);
		// choix1.setEnseignement(ens1);
		// Choix choix2 = new Choix();
		// choix2.setEnseignant(user1);
		// choix2.setEnseignement(ens1);
		// choixService.ajouter(choix1);
		// choixService.ajouter(choix2);

	}
}