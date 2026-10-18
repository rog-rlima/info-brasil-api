package com.rogertech.infobrasil.modules.zipcode.model;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ZipcodeMapper {
    AddressResponse toResponse(AddressDTO dto);
}
