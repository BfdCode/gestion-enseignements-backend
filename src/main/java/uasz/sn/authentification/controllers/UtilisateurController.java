package uasz.sn.authentification.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uasz.sn.authentification.modeles.Utilisateur;
import uasz.sn.authentification.repositories.UtilisateurRepository;
import uasz.sn.authentification.services.UtilisateurService;

@RestController
@RequestMapping("/api")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/users")
    public Iterable<Utilisateur> lister_utilisateur() {
        return utilisateurRepository.findAll();
    }

}