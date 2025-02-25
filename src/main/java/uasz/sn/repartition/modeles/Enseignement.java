package uasz.sn.repartition.modeles;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.syllabus.modeles.Ec;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enseignement implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @OneToMany
    private List<Choix> choix;

    @ManyToOne
    private Ec ec;
}
