package uasz.sn.maquette.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import uasz.sn.maquette.modeles.Maquette;
import uasz.sn.maquette.modeles.Classe;
import uasz.sn.maquette.repositories.MaquetteRepository;
import uasz.sn.maquette.repositories.ClasseRepository;

@Service
@Transactional
public class MaquetteService {
    @Autowired
    private MaquetteRepository maquetteRepository;

    @Autowired
    private ClasseRepository classeRepository;

    // Récupérer tous les ECs d'une UE
    public List<Maquette> rechercherMaquettes_classeId(Long classeId) {
        return maquetteRepository.findByClasseId(classeId);
    }

    public Optional<Maquette> rechercher_maquette_classeId(Long classeId, Long maquetteId) {
        List<Maquette> maquettes = rechercherMaquettes_classeId(classeId);
        if (maquettes == null) {
            return Optional.empty();
        }
        return maquettes.stream()
                        .filter(maquette -> maquette.getId().equals(maquetteId))
                        .findFirst();
    }

    // Ajouter un EC à une UE
    public Maquette ajouterMaquette(Long classeId, Maquette maquette) {
        Optional<Classe> classeOptional = classeRepository.findById(classeId);
        if (classeOptional.isPresent()) {
            Classe classe = classeOptional.get();
            maquette.setClasse(classe); // Associer l'EC à l'UE
            return maquetteRepository.save(maquette);
        } else {
            throw new RuntimeException("Classe non trouvée avmaquette l'ID : " + classeId);
        }
    }

    // Mettre à jour un EC
    public ResponseEntity<Maquette> modifierMaquette(Long classeId, Long maquetteId, Maquette maquetteDetails) {
        Optional<Maquette> maquetteOptional = maquetteRepository.findById(maquetteId);
        if (maquetteOptional.isPresent()) {
            Maquette existingMaquette = maquetteOptional.get();
            BeanUtils.copyProperties(maquetteDetails, existingMaquette, "id");
            Maquette updatedMaquette = maquetteRepository.save(existingMaquette);
            return new ResponseEntity<>(updatedMaquette, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ajouterMaquette(classeId, maquetteDetails), HttpStatus.CREATED);
        }
    }

    // Supprimer un EC
    public void supprimerMaquette(Long classeId, Long maquetteId) {
        Optional<Maquette> maquetteExiste = rechercher_maquette_classeId(classeId, maquetteId);
        if (!maquetteExiste.isEmpty()){
            maquetteRepository.deleteById(maquetteId);
        }
    }
    
}

