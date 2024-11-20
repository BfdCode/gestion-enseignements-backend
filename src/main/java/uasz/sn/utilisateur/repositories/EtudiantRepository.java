package uasz.sn.utilisateur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uasz.sn.utilisateur.modeles.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{}
