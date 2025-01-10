package uasz.sn.utilisateur.dtoRestController;

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

import uasz.sn.utilisateur.dto.VacataireDTO;
import uasz.sn.utilisateur.mapper.VacataireMapper;
import uasz.sn.utilisateur.modeles.Vacataire;
import uasz.sn.utilisateur.services.VacataireService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/apiDTO")
public class VacataireDTORestController {
    @Autowired
    private VacataireService vacataireService;

    private VacataireMapper vacataireMapper = Mappers.getMapper(VacataireMapper.class);

    @GetMapping("/vacataires")
    public ResponseEntity<List<VacataireDTO>> lister() {
        List<VacataireDTO> productList = vacataireMapper.vacataireToDTOs(vacataireService.lister());
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/vacataire/{id}")
    public ResponseEntity<VacataireDTO> rechercher(@PathVariable Long id) {
        Optional<Vacataire> OptionalVacataire = vacataireService.rechercher(id);
         if (OptionalVacataire.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Vacataire vacataire = OptionalVacataire.get();
        VacataireDTO vacataireDTO = vacataireMapper.vacataireToDTO(vacataire);
        return ResponseEntity.ok(vacataireDTO);
    }

    @PostMapping("/vacataire")
    public ResponseEntity<VacataireDTO> ajouter(@RequestBody VacataireDTO vacataireDTO) {
        Vacataire vacataire = vacataireMapper.dtoToVacataire(vacataireDTO);
        vacataireService.ajouter(vacataire);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacataireDTO);
    } 

    @PutMapping("/vacataire/{id}")
    public ResponseEntity<VacataireDTO> modifier(@PathVariable Long id, @RequestBody VacataireDTO vacataireDTO) {
        Optional<Vacataire> optionalVacataire = vacataireService.rechercher(id);
        Vacataire vacataire = optionalVacataire.get();
        vacataire.setUsername(vacataireDTO.getUsername());
        vacataire.setNom(vacataireDTO.getNom());
        vacataire.setPrenom(vacataireDTO.getPrenom());
        vacataire.setNiveau(vacataireDTO.getNiveau());
        vacataireService.modifier(id, vacataire);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(vacataireDTO);
    }

    @DeleteMapping("/vacataire/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        vacataireService.supprimer(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
