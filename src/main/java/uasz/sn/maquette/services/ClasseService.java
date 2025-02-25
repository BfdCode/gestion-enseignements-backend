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
import uasz.sn.maquette.repositories.ClasseRepository;
import uasz.sn.maquette.repositories.FormationRepository;

@Service
@Transactional
public class ClasseService {
    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private FormationRepository formationRepository;

    // Récupérer tous les ECs d'une UE
    public List<Classe> rechercherClasses_formationId(Long formationId) {
        return classeRepository.findByFormationId(formationId);
    }

    public Optional<Classe> rechercher_classe_formationId(Long formationId, Long classeId) {
        List<Classe> classes = rechercherClasses_formationId(formationId);
        if (classes == null) {
            return Optional.empty();
        }
        return classes.stream()
                        .filter(classe -> classe.getId().equals(classeId))
                        .findFirst();
    }

    // Ajouter un EC à une UE
    public Classe ajouterClasse(Long formationId, Classe classe) {
        Optional<Formation> formationOptional = formationRepository.findById(formationId);
        if (formationOptional.isPresent()) {
            Formation formation = formationOptional.get();
            classe.setFormation(formation); // Associer l'EC à l'UE
            return classeRepository.save(classe);
        } else {
            throw new RuntimeException("Formation non trouvée avec l'ID : " + formationId);
        }
    }

    // Mettre à jour un EC
    public ResponseEntity<Classe> modifierClasse(Long formationId, Long classeId, Classe classeDetails) {
        Optional<Classe> classeOptional = classeRepository.findById(classeId);
        if (classeOptional.isPresent()) {
            Classe existingClasse = classeOptional.get();
            BeanUtils.copyProperties(classeDetails, existingClasse, "id");
            Classe updatedClasse = classeRepository.save(existingClasse);
            return new ResponseEntity<>(updatedClasse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ajouterClasse(formationId, classeDetails), HttpStatus.CREATED);
        }
    }

    // Supprimer un EC
    public void supprimerClasse(Long formationId, Long classeId) {
        Optional<Classe> classeExiste = rechercher_classe_formationId(formationId, classeId);
        if (!classeExiste.isEmpty()){
            classeRepository.deleteById(classeId);
        }
    }

}

