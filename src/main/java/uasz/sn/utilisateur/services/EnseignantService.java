package uasz.sn.utilisateur.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import uasz.sn.utilisateur.modeles.Enseignant;
import uasz.sn.utilisateur.repositories.EnseignantRepository;

@Service
@Transactional
public class EnseignantService {
    @Autowired
    private EnseignantRepository enseignantRepository;

    public Iterable<Enseignant> lister() {
        return enseignantRepository.findAll();
    }

    public Optional<Enseignant> rEnseignanthercher(Long id){
        for (Enseignant enseignant : enseignantRepository.findAll()) {
            if (enseignant.getId().equals(id)) {
                return Optional.of(enseignant);
            }
        }

        return Optional.empty();
    }

    public Enseignant ajouter(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public ResponseEntity<Enseignant> modifier(Long id, Enseignant enseignant) {

        Optional<Enseignant> optionalEnseignant = enseignantRepository.findById(id);
    
        if (optionalEnseignant.isPresent()) {
            Enseignant existingEnseignant = optionalEnseignant.get();
            BeanUtils.copyProperties(enseignant, existingEnseignant, "id");
            Enseignant updatedEnseignant = enseignantRepository.save(existingEnseignant);
            return new ResponseEntity<>(updatedEnseignant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ajouter(enseignant), HttpStatus.CREATED);
        }
    }

    public void supprimer(Long id) {
        enseignantRepository.deleteById(id);
    }
}
