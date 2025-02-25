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

import uasz.sn.repartition.modeles.Choix;
import uasz.sn.repartition.services.ChoixService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ChoixController {
    @Autowired
    private ChoixService choixService;

    @GetMapping("/choixs")
    public Iterable<Choix> lister_choix() {return choixService.lister();}

    @PostMapping("/choix")
    public Choix ajouter_choix(@RequestBody Choix choix) {
        return choixService.ajouter(choix);
    }

    @GetMapping("/choix/{id}")
    public Optional<Choix> rechercher_choix(@PathVariable Long id) {
        return choixService.rechercher(id);
    }
    
    @PutMapping("/choix/{id}")
    public ResponseEntity<Choix> modifier_choix(@PathVariable Long id, @RequestBody Choix choix) {
        return choixService.modifier(id, choix);
    }
    
    @DeleteMapping("/choix/{id}")
    public void supprimer_choix(@PathVariable Long id) {
        choixService.supprimer(id);
    }
}

