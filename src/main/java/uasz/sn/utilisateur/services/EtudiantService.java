package uasz.sn.utilisateur.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import uasz.sn.utilisateur.modeles.Etudiant;
import uasz.sn.utilisateur.repositories.EtudiantRepository;

@Service
@Transactional
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Iterable<Etudiant> lister() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> rechercher(Long id){
        for (Etudiant etudiant : etudiantRepository.findAll()) {
            if (etudiant.getId().equals(id)) {
                return Optional.of(etudiant);
            }
        }

        return Optional.empty();
    }

    public Etudiant ajouter(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public ResponseEntity<Etudiant> modifier(Long id, Etudiant etudiant) {

        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(id);
    
        if (optionalEtudiant.isPresent()) {
            Etudiant existingEtudiant = optionalEtudiant.get();
            BeanUtils.copyProperties(etudiant, existingEtudiant, "id");
            Etudiant updatedEtudiant = etudiantRepository.save(existingEtudiant);
            return new ResponseEntity<>(updatedEtudiant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ajouter(etudiant), HttpStatus.CREATED);
        }
    }

    public void supprimer(Long id) {
        etudiantRepository.deleteById(id);
    }
    
}