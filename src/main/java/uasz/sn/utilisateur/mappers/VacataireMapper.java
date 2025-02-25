package uasz.sn.utilisateur.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import uasz.sn.utilisateur.dtos.VacataireDTO;
import uasz.sn.utilisateur.modeles.Vacataire;

@Mapper
public interface VacataireMapper {
    VacataireDTO vacataireToDTO(Vacataire vacataire);

    @Mapping(target = "password", defaultValue = "defaultPassword")
    Vacataire dtoToVacataire(VacataireDTO vacataireDTO);
    
    List<VacataireDTO> vacataireToDTOs(Iterable<Vacataire> vacataires);
} 
