package uasz.sn.utilisateur.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uasz.sn.utilisateur.modeles.Vacataire;
import uasz.sn.utilisateur.services.VacataireService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class VacataireController {
    @Autowired
    private VacataireService vacataireService;

    @GetMapping("/vacataires")
    public Iterable<Vacataire> lister_vacataire() {return vacataireService.lister();}

    @PostMapping("/vacataire")
    public Vacataire ajouter_vacataire(@RequestBody Vacataire vacataire) {
        return vacataireService.ajouter(vacataire);
    }

    @GetMapping("/vacataire/{id}")
    public Optional<Vacataire> rechercher_vacataire(@PathVariable Long id) {
        return vacataireService.rechercher(id);
    }
    
    @PutMapping("/vacataire/{id}")
    public ResponseEntity<Vacataire> modifier_vacataire(@PathVariable Long id, @RequestBody Vacataire vacataire) {
        return vacataireService.modifier(id, vacataire);
    }
    
    @DeleteMapping("/vacataire/{id}")
    public void supprimer_vacataire(@PathVariable Long id) {
        vacataireService.supprimer(id);
    }
}
