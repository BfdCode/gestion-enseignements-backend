package uasz.sn.utilisateur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uasz.sn.utilisateur.modeles.Enseignant;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long>{}
