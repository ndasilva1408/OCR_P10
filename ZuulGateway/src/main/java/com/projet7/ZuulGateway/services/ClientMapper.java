package com.projet7.ZuulGateway.services;

import com.projet7.ZuulGateway.model.Client;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface ClientMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ClientDTO toClient(Client entity);

    Client toDto(ClientDTO dto);
}
