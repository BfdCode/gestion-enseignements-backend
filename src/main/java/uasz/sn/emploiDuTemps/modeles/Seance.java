package uasz.sn.emploiDuTemps.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.repartition.modeles.Enseignement;
import uasz.sn.utilisateur.modeles.Enseignant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String horaire;
    private Enseignement enseignement;
    private String salle;
    private Enseignant enseignant;
}
