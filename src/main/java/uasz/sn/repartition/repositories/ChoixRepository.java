package uasz.sn.repartition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uasz.sn.repartition.modeles.Choix;

@Repository
public interface ChoixRepository extends JpaRepository<Choix, Long>{
    
}
