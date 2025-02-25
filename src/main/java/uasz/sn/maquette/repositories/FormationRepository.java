package uasz.sn.maquette.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uasz.sn.maquette.modeles.Classe;
import uasz.sn.maquette.modeles.Formation;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long>{
    @Query("SELECT e FROM Classe e WHERE e.formation = ?1")
    List<Classe> findByFormation(Formation formation);
}
