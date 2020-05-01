package com.example.clientms.services;

import com.example.clientms.entity.Client;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface ClientMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ClientDTO toClient(Client entity);

    Client toDto(ClientDTO dto);
}
