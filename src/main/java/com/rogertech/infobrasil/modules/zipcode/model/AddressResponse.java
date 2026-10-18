package com.rogertech.infobrasil.modules.zipcode.model;

public record AddressResponse(String cep,
                              String logradouro,
                              String bairro,
                              String localidade,
                              String uf,
                              String estado,
                              String regiao,
                              String ibge,
                              String gia,
                              String ddd,
                              String siafi,
                              Boolean error) {

    public static AddressResponse FromDTO(AddressDTO dto) {
        return new AddressResponse(
                dto.cep(),
                dto.logradouro(),
                dto.bairro(),
                dto.localidade(),
                dto.uf(),
                dto.estado(),
                dto.regiao(),
                dto.ibge(),
                dto.gia(),
                dto.ddd(),
                dto.siafi(),
                dto.isError()
        );
    }
}
