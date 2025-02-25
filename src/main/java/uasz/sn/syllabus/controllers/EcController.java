package uasz.sn.syllabus.controllers;

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

import uasz.sn.syllabus.modeles.Ec;
import uasz.sn.syllabus.services.EcService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/syllabus/ecs/ue")
public class EcController {

    @Autowired
    private EcService ecService;

    // Récupérer tous les ECs d'une UE
    @GetMapping("/{ueId}/ecs")
    public List<Ec> rechercher_ecs_ueId(@PathVariable Long ueId) {
        return ecService.rechercherEcs_ueId(ueId);
    }

    @GetMapping("/{ueId}/ec/{ecId}")
    public Optional<Ec> rechercher_ec_ueId(@PathVariable Long ueId,@PathVariable Long ecId) {
        return ecService.rechercher_ec_ueId(ueId, ecId);
    }

    // Ajouter un EC à une UE
    @PostMapping("/{ueId}/ec")
    public Ec ajouter_ec_ue(@PathVariable Long ueId, @RequestBody Ec ec) {
        return ecService.ajouterEc(ueId, ec);
    }

    // Mettre à jour un EC
    @PutMapping("/{ueId}/ec/{ecId}")
    public ResponseEntity<Ec> modifier_ec_ue(@PathVariable Long ueId, @PathVariable Long ecId, @RequestBody Ec ecDetails) {
        return ecService.modifierEc(ueId, ecId, ecDetails);
    }

    // Supprimer un EC
    @DeleteMapping("/{ueId}/ec/{ecId}")
    public ResponseEntity<Void> supprimer_ec_ue(@PathVariable Long ueId, @PathVariable Long ecId) {
        ecService.supprimerEc(ueId, ecId);
        return ResponseEntity.noContent().build();
    }
}


