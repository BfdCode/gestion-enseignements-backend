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

import uasz.sn.utilisateur.dtos.PermanentDTO;
import uasz.sn.utilisateur.mappers.PermanentMapper;
import uasz.sn.utilisateur.modeles.Permanent;
import uasz.sn.utilisateur.services.PermanentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/apiDTO")
public class PermanentDTORestController {
    @Autowired
    private PermanentService permanentService;

    private PermanentMapper permanentMapper = Mappers.getMapper(PermanentMapper.class);

    @GetMapping("/permanents")
    public ResponseEntity<List<PermanentDTO>> lister() {
        List<PermanentDTO> productList = permanentMapper.permanentToDTOs(permanentService.lister());
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/permanent/{id}")
    public ResponseEntity<PermanentDTO> rechercher(@PathVariable Long id) {
        Optional<Permanent> OptionalPermanent = permanentService.rechercher(id);
        if (OptionalPermanent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Permanent permanent = OptionalPermanent.get();
        PermanentDTO permanentDTO = permanentMapper.permanentToDTO(permanent);
        return ResponseEntity.ok(permanentDTO);
    }

    @PostMapping("/permanent")
    public ResponseEntity<PermanentDTO> ajouter(@RequestBody PermanentDTO permanentDTO) {
        Permanent permanent = permanentMapper.dtoToPermanent(permanentDTO);
        permanentService.ajouter(permanent);
        return ResponseEntity.status(HttpStatus.CREATED).body(permanentDTO);
    } 

    @PutMapping("/permanent/{id}")
    public ResponseEntity<PermanentDTO> modifier(@PathVariable Long id, @RequestBody PermanentDTO permanentDTO) {
        Optional<Permanent> optionalPermanent = permanentService.rechercher(id);
        Permanent permanent = optionalPermanent.get();
        permanent.setUsername(permanentDTO.getUsername());
        permanent.setNom(permanentDTO.getNom());
        permanent.setPrenom(permanentDTO.getPrenom());
        permanent.setMatricule(permanentDTO.getMatricule());
        permanent.setGrade(permanentDTO.getGrade());
        permanentService.modifier(id, permanent);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(permanentDTO);
    }

    @DeleteMapping("/permanent/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        permanentService.supprimer(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
