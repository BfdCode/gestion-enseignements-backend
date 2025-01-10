package uasz.sn.utilisateur.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDTO {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String nom;
    private String prenom;
    private String matricule;
}
