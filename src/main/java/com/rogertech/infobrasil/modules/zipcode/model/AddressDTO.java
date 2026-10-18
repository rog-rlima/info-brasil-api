package com.rogertech.infobrasil.modules.zipcode.model;

public record AddressDTO(String cep,
                         String logradouro,
                         String complemento,
                         String unidade,
                         String bairro,
                         String localidade,
                         String uf,
                         String estado,
                         String regiao,
                         String ibge,
                         String gia,
                         String ddd,
                         String siafi,
                         Boolean erro) {

    public boolean isError() {
        return Boolean.TRUE.equals(erro);
    }
}
