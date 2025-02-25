package uasz.sn.utilisateur.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import uasz.sn.utilisateur.dtos.PermanentDTO;
import uasz.sn.utilisateur.modeles.Permanent;

@Mapper
public interface PermanentMapper {
    PermanentDTO permanentToDTO(Permanent permanent);

    @Mapping(target = "password", defaultValue = "defaultPassword")
    Permanent dtoToPermanent(PermanentDTO permanentDTO);
    
    List<PermanentDTO> permanentToDTOs(Iterable<Permanent> permanents);
}
