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
import uasz.sn.maquette.modeles.Formation;
import uasz.sn.maquette.services.FormationService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/maquette")
public class FormationController {
    @Autowired
    private FormationService formationService;

    @GetMapping("/formations")
    public Iterable<Formation> lister_formation() {return formationService.lister();}

    @PostMapping("/formation")
    public Formation ajouter_formation(@RequestBody Formation formation) {
        return formationService.ajouter(formation);
    }

    @GetMapping("/formation/{id}")
    public Optional<Formation> rechercher_formation(@PathVariable Long id) {
        return formationService.rechercher(id);
    }
    
    @PutMapping("/formation/{id}")
    public ResponseEntity<Formation> modifier_formation(@PathVariable Long id, @RequestBody Formation formation) {
        return formationService.modifier(id, formation);
    }
    
    @DeleteMapping("/formation/{id}")
    public void supprimer_formation(@PathVariable Long id) {
        formationService.supprimer(id);
    }

    @GetMapping("/formation/{formationId}/classes")
    public ResponseEntity<List<Classe>> lister_classes_formation(@PathVariable Long formationId) {
        Optional<Formation> OptionalFormation = formationService.rechercher(formationId); // Récupérer l'UE par son ID
        if (OptionalFormation.isPresent()){
            List<Classe> classes = formationService.lister_Classe(OptionalFormation.get()); // Récupérer les ECs associés
            return ResponseEntity.ok(classes);
        }
        return ResponseEntity.notFound().build();
    }
}