package uasz.sn.syllabus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uasz.sn.syllabus.modeles.Ec;

@Repository
public interface EcRepository extends JpaRepository<Ec, Long>{
     List<Ec> findByUeId(Long ueId);
}