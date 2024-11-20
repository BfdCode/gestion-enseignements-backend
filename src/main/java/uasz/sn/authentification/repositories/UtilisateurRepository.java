package uasz.sn.authentification.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import uasz.sn.authentification.modeles.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    @Query("SELECT u FROM Utilisateur u WHERE u.username = :username")
    Optional<Utilisateur> findUtilisateurByUsername(@Param("username") String username);
}
