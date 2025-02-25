package uasz.sn.maquette.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uasz.sn.maquette.modeles.Classe;
import uasz.sn.maquette.modeles.Maquette;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long>{
    List<Classe> findByFormationId(Long formationId); 

    @Query("SELECT e FROM Maquette e WHERE e.classe = ?1")
    List<Maquette> findByClasse(Classe classe);
}
