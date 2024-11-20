package uasz.sn.utilisateur.services;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<Etudiant> modifier(Long id, Etudiant etudiant){
        int indexEtudiant = -1;
        List<Etudiant> etudiants = etudiantRepository.findAll();

        for (Etudiant e : etudiants) {
            if (e.getId().equals(id)) {
                indexEtudiant = etudiants.indexOf(e);
                etudiants.set(indexEtudiant, etudiant);
            }
        }
        
        return (indexEtudiant == -1) ? 
            new ResponseEntity<>(ajouter(etudiant), HttpStatus.CREATED) :
            new ResponseEntity<>(ajouter(etudiant), HttpStatus.OK) ;
    }

    public void supprimer(Long id) {
        etudiantRepository.deleteById(id);
    }
    
}