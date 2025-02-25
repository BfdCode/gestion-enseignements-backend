package uasz.sn.repartition.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import uasz.sn.repartition.modeles.Enseignement;
import uasz.sn.repartition.repositories.EnseignementRepository;

@Service
@Transactional
public class EnseignementService {

    @Autowired
    private EnseignementRepository enseignementRepository;

    public Iterable<Enseignement> lister() {
        return enseignementRepository.findAll();
    }

    public Optional<Enseignement> rechercher(Long id){
        for (Enseignement enseignement : enseignementRepository.findAll()) {
            if (enseignement.getId().equals(id)) {
                return Optional.of(enseignement);
            }
        }

        return Optional.empty();
    }

    public Enseignement ajouter(Enseignement enseignement) {
        return enseignementRepository.save(enseignement);
    }

   public ResponseEntity<Enseignement> modifier(Long id, Enseignement enseignement) {

        Optional<Enseignement> optionalEnseignement = enseignementRepository.findById(id);
    
        if (optionalEnseignement.isPresent()) {
            Enseignement existingEnseignement = optionalEnseignement.get();
            BeanUtils.copyProperties(enseignement, existingEnseignement, "id");
            Enseignement updatedEnseignement = enseignementRepository.save(existingEnseignement);
            return new ResponseEntity<>(updatedEnseignement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ajouter(enseignement), HttpStatus.CREATED);
        }
    }

    public void supprimer(Long id) {
        enseignementRepository.deleteById(id);
    }
    
}
