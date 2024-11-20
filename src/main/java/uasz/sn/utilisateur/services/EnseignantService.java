package uasz.sn.utilisateur.services;

import java.util.List;
import java.util.Optional;

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

    public Optional<Enseignant> rechercher(Long id){
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

    public ResponseEntity<Enseignant> modifier(Long id, Enseignant enseignant){
        int indexEnseignant = -1;
        List<Enseignant> enseignants = enseignantRepository.findAll();

        for (Enseignant e : enseignants) {
            if (e.getId().equals(id)) {
                indexEnseignant = enseignants.indexOf(e);
                enseignants.set(indexEnseignant, enseignant);
            }
        }
        
        return (indexEnseignant == -1) ? 
            new ResponseEntity<>(ajouter(enseignant), HttpStatus.CREATED) :
            new ResponseEntity<>(ajouter(enseignant), HttpStatus.OK) ;
    }

    public void supprimer(Long id) {
        enseignantRepository.deleteById(id);
    }
}
