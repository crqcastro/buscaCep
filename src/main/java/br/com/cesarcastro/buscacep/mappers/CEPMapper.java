package br.com.cesarcastro.buscacep.mappers;

import br.com.cesarcastro.buscacep.domain.model.entities.CEPEntity;
import br.com.cesarcastro.buscacep.domain.model.response.CEPResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CEPMapper {
    CEPMapper INSTANCE = Mappers.getMapper(CEPMapper.class);

    CEPResponse toCEPResponse(CEPEntity entity);

    CEPEntity toEntity(CEPResponse cepResponse);
}
