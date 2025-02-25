package uasz.sn.utilisateur.dtoRestControllers;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import uasz.sn.utilisateur.dtos.EtudiantDTO;
import uasz.sn.utilisateur.mappers.EtudiantMapper;
import uasz.sn.utilisateur.modeles.Etudiant;
import uasz.sn.utilisateur.services.EtudiantService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/apiDTO")
public class EtudiantDTORestController {
    @Autowired
    private EtudiantService etudiantService;

    private EtudiantMapper etudiantMapper = Mappers.getMapper(EtudiantMapper.class);

    @GetMapping("/etudiants")
    public ResponseEntity<List<EtudiantDTO>> lister() {
        List<EtudiantDTO> productList = etudiantMapper.etudiantToDTOs(etudiantService.lister());
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/etudiant/{id}")
    public ResponseEntity<EtudiantDTO> rechercher(@PathVariable Long id) {
        Optional<Etudiant> optionalEtudiant = etudiantService.rechercher(id);
        if (optionalEtudiant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Etudiant etudiant = optionalEtudiant.get();
        EtudiantDTO etudiantDTO = etudiantMapper.etudiantToDTO(etudiant);
        return ResponseEntity.ok(etudiantDTO);
    }

    @PostMapping("/etudiant")
    public ResponseEntity<EtudiantDTO> ajouter(@RequestBody EtudiantDTO etudiantDTO) {
        Etudiant etudiant = etudiantMapper.dtoToEtudiant(etudiantDTO);
        etudiantService.ajouter(etudiant);
        return ResponseEntity.status(HttpStatus.CREATED).body(etudiantDTO);
    } 

    @PutMapping("/etudiant/{id}")
    public ResponseEntity<EtudiantDTO> modifier(@PathVariable Long id, @RequestBody EtudiantDTO etudiantDTO) {
        Optional<Etudiant> optionalEtudiant = etudiantService.rechercher(id);
        Etudiant etudiant = optionalEtudiant.get();
        etudiant.setUsername(etudiantDTO.getUsername());
        etudiant.setNom(etudiantDTO.getNom());
        etudiant.setPrenom(etudiantDTO.getPrenom());
        etudiant.setMatricule(etudiantDTO.getMatricule());
        etudiantService.modifier(id, etudiant);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(etudiantDTO);
    }

    @DeleteMapping("/etudiant/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        etudiantService.supprimer(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
