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

import uasz.sn.maquette.modeles.Maquette;
import uasz.sn.maquette.services.MaquetteService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/maquette/maquettes/classe")
public class MaquetteController {

    @Autowired
    private MaquetteService maquetteService;

    // Récupérer tous les ECs d'une UE
    @GetMapping("/{classeId}/maquettes")
    public List<Maquette> rechercher_maquettes_classeId(@PathVariable Long classeId) {
        return maquetteService.rechercherMaquettes_classeId(classeId);
    }

    @GetMapping("/{classeId}/maquette/{maquetteId}")
    public Optional<Maquette> rechercher_maquette_classeId(@PathVariable Long classeId,@PathVariable Long maquetteId) {
        return maquetteService.rechercher_maquette_classeId(classeId, maquetteId);
    }

    // Ajouter un EC à une UE
    @PostMapping("/{classeId}/maquette")
    public Maquette ajouter_maquette_classe(@PathVariable Long classeId, @RequestBody Maquette maquette) {
        return maquetteService.ajouterMaquette(classeId, maquette);
    }

    // Mettre à jour un EC
    @PutMapping("/{classeId}/maquette/{maquetteId}")
    public ResponseEntity<Maquette> modifier_maquette_classe(@PathVariable Long classeId, @PathVariable Long maquetteId, @RequestBody Maquette maquetteDetails) {
        return maquetteService.modifierMaquette(classeId, maquetteId, maquetteDetails);
    }

    // Supprimer un EC
    @DeleteMapping("/{classeId}/maquette/{maquetteId}")
    public ResponseEntity<Void> supprimer_maquette_classe(@PathVariable Long classeId, @PathVariable Long maquetteId) {
        maquetteService.supprimerMaquette(classeId, maquetteId);
        return ResponseEntity.noContent().build();
    }
}


