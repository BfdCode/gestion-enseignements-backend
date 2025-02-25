package uasz.sn.utilisateur.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import uasz.sn.utilisateur.modeles.Permanent;
import uasz.sn.utilisateur.repositories.PermanentRepository;

@Service
@Transactional
public class PermanentService {
    @Autowired
    private PermanentRepository permanentRepository;

    public Iterable<Permanent> lister() {
        return permanentRepository.findAll();
    }

    public Optional<Permanent> rechercher(Long id){
        for (Permanent permanent : permanentRepository.findAll()) {
            if (permanent.getId().equals(id)) {
                return Optional.of(permanent);
            }
        }

        return Optional.empty();
    }

    public Permanent ajouter(Permanent permanent) {
        return permanentRepository.save(permanent);
    }

    public ResponseEntity<Permanent> modifier(Long id, Permanent permanent) {

        Optional<Permanent> optionalPermanent = permanentRepository.findById(id);
    
        if (optionalPermanent.isPresent()) {
            Permanent existingPermanent = optionalPermanent.get();
            BeanUtils.copyProperties(permanent, existingPermanent, "id");
            Permanent updatedPermanent = permanentRepository.save(existingPermanent);
            return new ResponseEntity<>(updatedPermanent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ajouter(permanent), HttpStatus.CREATED);
        }
    }

    public void supprimer(Long id) {
        permanentRepository.deleteById(id);
    }
}
