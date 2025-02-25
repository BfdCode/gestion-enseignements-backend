package uasz.sn.repartition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uasz.sn.repartition.modeles.Enseignement;

@Repository
public interface EnseignementRepository extends JpaRepository<Enseignement, Long>{
    
}
