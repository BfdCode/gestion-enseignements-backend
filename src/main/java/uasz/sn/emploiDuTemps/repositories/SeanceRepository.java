package uasz.sn.emploiDuTemps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uasz.sn.emploiDuTemps.modeles.Seance;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long>{
    
}
