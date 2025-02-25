package uasz.sn.syllabus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import uasz.sn.syllabus.modeles.Ec;
import uasz.sn.syllabus.modeles.Ue;
import uasz.sn.syllabus.repositories.EcRepository;
import uasz.sn.syllabus.repositories.UeRepository;

@Service
@Transactional
public class EcService {
    @Autowired
    private EcRepository ecRepository;

    @Autowired
    private UeRepository ueRepository;

    // Récupérer tous les ECs d'une UE
    public List<Ec> rechercherEcs_ueId(Long ueId) {
        return ecRepository.findByUeId(ueId);
    }

    public Optional<Ec> rechercher_ec_ueId(Long ueId, Long ecId) {
        List<Ec> ecs = rechercherEcs_ueId(ueId);
        if (ecs == null) {
            return Optional.empty();
        }
        return ecs.stream()
                        .filter(ec -> ec.getId().equals(ecId))
                        .findFirst();
    }

    // Ajouter un EC à une UE
    public Ec ajouterEc(Long ueId, Ec ec) {
        Optional<Ue> ueOptional = ueRepository.findById(ueId);
        if (ueOptional.isPresent()) {
            Ue ue = ueOptional.get();
            ec.setUe(ue); // Associer l'EC à l'UE
            return ecRepository.save(ec);
        } else {
            throw new RuntimeException("UE non trouvée avec l'ID : " + ueId);
        }
    }

    // Mettre à jour un EC
    public ResponseEntity<Ec> modifierEc(Long ueId, Long ecId, Ec ecDetails) {
        Optional<Ec> ecOptional = ecRepository.findById(ecId);
        if (ecOptional.isPresent()) {
            Ec existingEc = ecOptional.get();
            BeanUtils.copyProperties(ecDetails, existingEc, "id");
            Ec updatedEc = ecRepository.save(existingEc);
            return new ResponseEntity<>(updatedEc, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ajouterEc(ueId, ecDetails), HttpStatus.CREATED);
        }
    }

    // Supprimer un EC
    public void supprimerEc(Long ueId, Long ecId) {
        Optional<Ec> ecExiste = rechercher_ec_ueId(ueId, ecId);
        if (!ecExiste.isEmpty()){
            ecRepository.deleteById(ecId);
        }
    }
    
}

