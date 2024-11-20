package uasz.sn.utilisateur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uasz.sn.utilisateur.modeles.Vacataire;

@Repository
public interface VacataireRepository extends JpaRepository<Vacataire, Long>{}
