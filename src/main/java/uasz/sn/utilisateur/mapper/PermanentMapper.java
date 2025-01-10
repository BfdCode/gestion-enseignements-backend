package uasz.sn.utilisateur.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import uasz.sn.utilisateur.dto.PermanentDTO;
import uasz.sn.utilisateur.modeles.Permanent;

@Mapper
public interface PermanentMapper {
    PermanentDTO permanentToDTO(Permanent permanent);
    @Mapping(target = "password", defaultValue = "defaultPassword")
    Permanent dtoToPermanent(PermanentDTO permanentDTO);
    List<PermanentDTO> permanentToDTOs(Iterable<Permanent> permanents);
}
