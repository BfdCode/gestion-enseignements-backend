package uasz.sn.syllabus.modeles;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.maquette.modeles.Maquette;
import uasz.sn.repartition.modeles.Enseignement;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private String code;
    private int credit;
    private int coefficient;
    private String description;
    private Date dateCreation;
    // private Utilisateur utilisateur;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "ue")
    private List<Ec> ecs;

    @ManyToMany
    private Collection<Maquette> maquette;

    @OneToMany
    private List<Enseignement> enseignements;
}
