package uasz.sn.maquette.controllers;

import java.util.List;
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

import uasz.sn.maquette.modeles.Classe;
import uasz.sn.maquette.services.ClasseService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/maquette/classes/formation")
public class ClasseController {

    @Autowired
    private ClasseService classeService;

    // Récupérer tous les ECs d'une UE
    @GetMapping("/{formationId}/classes")
    public List<Classe> rechercher_classes_formationId(@PathVariable Long formationId) {
        return classeService.rechercherClasses_formationId(formationId);
    }

    @GetMapping("/{formationId}/classe/{classeId}")
    public Optional<Classe> rechercher_classe_formationId(@PathVariable Long formationId,@PathVariable Long classeId) {
        return classeService.rechercher_classe_formationId(formationId, classeId);
    }

    // Ajouter un EC à une UE
    @PostMapping("/{formationId}/classe")
    public Classe ajouter_classe_formation(@PathVariable Long formationId, @RequestBody Classe classe) {
        return classeService.ajouterClasse(formationId, classe);
    }

    // Mettre à jour un EC
    @PutMapping("/{formationId}/classe/{classeId}")
    public ResponseEntity<Classe> modifier_classe_formation(@PathVariable Long formationId, @PathVariable Long classeId, @RequestBody Classe classeDetails) {
        return classeService.modifierClasse(formationId, classeId, classeDetails);
    }

    // Supprimer un EC
    @DeleteMapping("/{formationId}/classe/{classeId}")
    public ResponseEntity<Void> supprimer_classe_formation(@PathVariable Long formationId, @PathVariable Long classeId) {
        classeService.supprimerClasse(formationId, classeId);
        return ResponseEntity.noContent().build();
    }
}


