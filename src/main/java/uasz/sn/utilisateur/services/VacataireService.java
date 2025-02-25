package uasz.sn.utilisateur.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import uasz.sn.utilisateur.modeles.Vacataire;
import uasz.sn.utilisateur.repositories.VacataireRepository;

@Service
@Transactional
public class VacataireService {
    @Autowired
    private VacataireRepository vacataireRepository;

    public Iterable<Vacataire> lister() {
        return vacataireRepository.findAll();
    }

    public Optional<Vacataire> rechercher(Long id){
        for (Vacataire vacataire : vacataireRepository.findAll()) {
            if (vacataire.getId().equals(id)) {
                return Optional.of(vacataire);
            }
        }

        return Optional.empty();
    }

    public Vacataire ajouter(Vacataire vacataire) {
        return vacataireRepository.save(vacataire);
    }

    public ResponseEntity<Vacataire> modifier(Long id, Vacataire vacataire) {

        Optional<Vacataire> optionalVacataire = vacataireRepository.findById(id);
    
        if (optionalVacataire.isPresent()) {
            Vacataire existingVacataire = optionalVacataire.get();
            BeanUtils.copyProperties(vacataire, existingVacataire, "id");
            Vacataire updatedVacataire = vacataireRepository.save(existingVacataire);
            return new ResponseEntity<>(updatedVacataire, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ajouter(vacataire), HttpStatus.CREATED);
        }
    }


    public void supprimer(Long id) {
        vacataireRepository.deleteById(id);
    }
}
