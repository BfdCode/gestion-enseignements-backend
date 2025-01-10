package uasz.sn.utilisateur.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import uasz.sn.utilisateur.dto.VacataireDTO;
import uasz.sn.utilisateur.modeles.Vacataire;

@Mapper
public interface VacataireMapper {
    VacataireDTO vacataireToDTO(Vacataire vacataire);
    @Mapping(target = "password", defaultValue = "defaultPassword")
    Vacataire dtoToVacataire(VacataireDTO vacataireDTO);
    List<VacataireDTO> vacataireToDTOs(Iterable<Vacataire> vacataires);
} 
