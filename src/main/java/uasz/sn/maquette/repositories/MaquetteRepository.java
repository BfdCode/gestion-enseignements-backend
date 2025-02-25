package uasz.sn.maquette.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uasz.sn.maquette.modeles.Maquette;

@Repository
public interface MaquetteRepository extends JpaRepository<Maquette, Long>{
     List<Maquette> findByClasseId(Long classeId);
}