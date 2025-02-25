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

import uasz.sn.utilisateur.modeles.Etudiant;
import uasz.sn.utilisateur.services.EtudiantService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("/etudiants")
    public Iterable<Etudiant> lister_etudiant() {return etudiantService.lister();}

    @PostMapping("/etudiant")
    public Etudiant ajouter_etudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.ajouter(etudiant);
    }

    @GetMapping("/etudiant/{id}")
    public Optional<Etudiant> rechercher_etudiant(@PathVariable Long id) {
        return etudiantService.rechercher(id);
    }
    
    @PutMapping("/etudiant/{id}")
    public ResponseEntity<Etudiant> modifier_etudiant(@PathVariable Long id, @RequestBody Etudiant etudiant) {
        return etudiantService.modifier(id, etudiant);
    }
    
    @DeleteMapping("/etudiant/{id}")
    public void supprimer_etudiant(@PathVariable Long id) {
        etudiantService.supprimer(id);
    }
}
