package uasz.sn.syllabus.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uasz.sn.syllabus.modeles.Ec;
import uasz.sn.syllabus.modeles.Ue;

@Repository
public interface UeRepository extends JpaRepository<Ue, Long>{
    @Query("SELECT e FROM Ec e WHERE e.ue = ?1")
    List<Ec> findByUe(Ue ue);
}
