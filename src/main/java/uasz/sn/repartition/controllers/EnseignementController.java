package uasz.sn.repartition.controllers;

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

import uasz.sn.repartition.modeles.Enseignement;
import uasz.sn.repartition.services.EnseignementService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class EnseignementController {
    @Autowired
    private EnseignementService enseignementService;

    @GetMapping("/enseignements")
    public Iterable<Enseignement> lister_enseignement() {return enseignementService.lister();}

    @PostMapping("/enseignement")
    public Enseignement ajouter_enseignement(@RequestBody Enseignement enseignement) {
        return enseignementService.ajouter(enseignement);
    }

    @GetMapping("/enseignement/{id}")
    public Optional<Enseignement> rechercher_enseignement(@PathVariable Long id) {
        return enseignementService.rechercher(id);
    }
    
    @PutMapping("/enseignement/{id}")
    public ResponseEntity<Enseignement> modifier_enseignement(@PathVariable Long id, @RequestBody Enseignement enseignement) {
        return enseignementService.modifier(id, enseignement);
    }
    
    @DeleteMapping("/enseignement/{id}")
    public void supprimer_enseignement(@PathVariable Long id) {
        enseignementService.supprimer(id);
    }
}

