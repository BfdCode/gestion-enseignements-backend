package uasz.sn.maquette.modeles;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.syllabus.modeles.Ue;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maquette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String semestre;
    
    @ManyToMany
    private List<Ue> ues;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe", nullable = false)
    private Classe classe;
}