package com.example.biblioms.services;

import com.example.biblioms.entity.Bibliotheque;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BiblioMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    BiblioDTO toBibliotheque(Bibliotheque entity);

    Bibliotheque toDTO(BiblioDTO dto);
}

