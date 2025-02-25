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
import uasz.sn.syllabus.modeles.Ue;
import uasz.sn.syllabus.services.UeService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/syllabus")
public class UeController {
    @Autowired
    private UeService ueService;

    @GetMapping("/ues")
    public Iterable<Ue> lister_ue() {return ueService.lister();}

    @PostMapping("/ue")
    public Ue ajouter_ue(@RequestBody Ue ue) {
        return ueService.ajouter(ue);
    }

    @GetMapping("/ue/{id}")
    public Optional<Ue> rechercher_ue(@PathVariable Long id) {
        return ueService.rechercher(id);
    }
    
    @PutMapping("/ue/{id}")
    public ResponseEntity<Ue> modifier_ue(@PathVariable Long id, @RequestBody Ue ue) {
        return ueService.modifier(id, ue);
    }
    
    @DeleteMapping("/ue/{id}")
    public void supprimer_ue(@PathVariable Long id) {
        ueService.supprimer(id);
    }

    @GetMapping("/ue/{ueId}/ecs")
    public ResponseEntity<List<Ec>> lister_ecs_ue(@PathVariable Long ueId) {
        Optional<Ue> OptionalUe = ueService.rechercher(ueId); // Récupérer l'UE par son ID
        if (OptionalUe.isPresent()){
            List<Ec> ecs = ueService.lister_Ec(OptionalUe.get()); // Récupérer les ECs associés
            return ResponseEntity.ok(ecs);
        }
        return ResponseEntity.notFound().build();
    }
}




