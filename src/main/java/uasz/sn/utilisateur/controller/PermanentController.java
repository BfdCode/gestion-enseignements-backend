package uasz.sn.utilisateur.controller;

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

import uasz.sn.utilisateur.modeles.Permanent;
import uasz.sn.utilisateur.services.PermanentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class PermanentController {
    @Autowired
    private PermanentService permanentService;

    @GetMapping("/permanents")
    public Iterable<Permanent> lister_permanent() {return permanentService.lister();}

    @PostMapping("/permanent")
    public Permanent ajouter_permanent(@RequestBody Permanent permanent) {
        return permanentService.ajouter(permanent);
    }

    @GetMapping("/permanent/{id}")
    public Optional<Permanent> rechercher_permanent(@PathVariable Long id) {
        return permanentService.rechercher(id);
    }
    
    @PutMapping("/permanent/{id}")
    public ResponseEntity<Permanent> modifier_permanent(@PathVariable Long id, @RequestBody Permanent permanent) {
        return permanentService.modifier(id, permanent);
    }
    
    @DeleteMapping("/permanent/{id}")
    public void supprimer_permanent(@PathVariable Long id) {
        permanentService.supprimer(id);
    }
}
