package uasz.sn.utilisateur.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import uasz.sn.utilisateur.dtos.EtudiantDTO;
import uasz.sn.utilisateur.modeles.Etudiant;

@Mapper
public interface EtudiantMapper {
    EtudiantDTO etudiantToDTO(Etudiant etudiant);

    @Mapping(target = "password", defaultValue = "defaultPassword") 
    Etudiant dtoToEtudiant(EtudiantDTO etudiantDTO);
    
    List<EtudiantDTO> etudiantToDTOs(Iterable<Etudiant> etudiants);
}