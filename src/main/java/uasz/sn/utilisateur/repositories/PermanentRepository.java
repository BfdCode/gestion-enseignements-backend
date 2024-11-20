package uasz.sn.utilisateur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uasz.sn.utilisateur.modeles.Permanent;

@Repository
public interface PermanentRepository extends JpaRepository<Permanent, Long>{}
