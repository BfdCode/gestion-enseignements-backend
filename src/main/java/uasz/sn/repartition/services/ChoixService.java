package uasz.sn.repartition.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import uasz.sn.repartition.modeles.Choix;
import uasz.sn.repartition.repositories.ChoixRepository;

@Service
@Transactional
public class ChoixService {

    @Autowired
    private ChoixRepository choixRepository;

    public Iterable<Choix> lister() {
        return choixRepository.findAll();
    }

    public Optional<Choix> rechercher(Long id){
        for (Choix choix : choixRepository.findAll()) {
            if (choix.getId().equals(id)) {
                return Optional.of(choix);
            }
        }

        return Optional.empty();
    }

    public Choix ajouter(Choix choix) {
        return choixRepository.save(choix);
    }

   public ResponseEntity<Choix> modifier(Long id, Choix choix) {

        Optional<Choix> optionalChoice = choixRepository.findById(id);
    
        if (optionalChoice.isPresent()) {
            Choix existingChoix = optionalChoice.get();
            BeanUtils.copyProperties(choix, existingChoix, "id");
            Choix updatedChoix = choixRepository.save(existingChoix);
            return new ResponseEntity<>(updatedChoix, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ajouter(choix), HttpStatus.CREATED);
        }
    }

    public void supprimer(Long id) {
        choixRepository.deleteById(id);
    }
    
}
