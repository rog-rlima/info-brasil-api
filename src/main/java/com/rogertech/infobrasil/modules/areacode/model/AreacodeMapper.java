package com.rogertech.infobrasil.modules.areacode.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AreacodeMapper {

    @Mapping(source = "state", target = "estado")
    @Mapping(source = "cities", target = "cidades")
    AreacodeResponse toResponse(AreacodeDTO areacodeDTO);
}
