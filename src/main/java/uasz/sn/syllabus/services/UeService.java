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
import uasz.sn.syllabus.repositories.UeRepository;

@Service
@Transactional
public class UeService {

    @Autowired
    private UeRepository ueRepository;

    public Iterable<Ue> lister() {
        return ueRepository.findAll();
    }

    public Optional<Ue> rechercher(Long id){
        for (Ue ue : ueRepository.findAll()) {
            if (ue.getId().equals(id)) {
                return Optional.of(ue);
            }
        }

        return Optional.empty();
    }

    public Ue ajouter(Ue ue) {
        return ueRepository.save(ue);
    }

    public ResponseEntity<Ue> modifier(Long id, Ue ue) {

        Optional<Ue> optionalUe = ueRepository.findById(id);
    
        if (optionalUe.isPresent()) {
            Ue existingUe = optionalUe.get();
            BeanUtils.copyProperties(ue, existingUe, "id");
            Ue updatedUe = ueRepository.save(existingUe);
            return new ResponseEntity<>(updatedUe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ajouter(ue), HttpStatus.CREATED);
        }
    }

    public void supprimer(Long id) {
        ueRepository.deleteById(id);
    }

    public List<Ec> lister_Ec(Ue ue) {
        return ueRepository.findByUe(ue);
    }

}



