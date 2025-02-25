package uasz.sn.maquette.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import uasz.sn.maquette.modeles.Classe;
import uasz.sn.maquette.modeles.Formation;
import uasz.sn.maquette.repositories.FormationRepository;

@Service
@Transactional
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    public Iterable<Formation> lister() {
        return formationRepository.findAll();
    }

    public Optional<Formation> rechercher(Long id){
        for (Formation formation : formationRepository.findAll()) {
            if (formation.getId().equals(id)) {
                return Optional.of(formation);
            }
        }

        return Optional.empty();
    }

    public Formation ajouter(Formation formation) {
        return formationRepository.save(formation);
    }

    public ResponseEntity<Formation> modifier(Long id, Formation formation) {

        Optional<Formation> optionalFormation = formationRepository.findById(id);
    
        if (optionalFormation.isPresent()) {
            Formation existingFormation = optionalFormation.get();
            BeanUtils.copyProperties(formation, existingFormation, "id");
            Formation updatedFormation = formationRepository.save(existingFormation);
            return new ResponseEntity<>(updatedFormation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ajouter(formation), HttpStatus.CREATED);
        }
    }

    public void supprimer(Long id) {
        formationRepository.deleteById(id);
    }

    public List<Classe> lister_Classe(Formation formation) {
        return formationRepository.findByFormation(formation);
    }

}



