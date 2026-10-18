package com.rogertech.infobrasil.modules.zipcode.clients;

import com.rogertech.infobrasil.modules.zipcode.model.AddressDTO;

public class ViaCepFallback implements ViaCepClient {

    @Override
    public AddressDTO getAddress(String cep) {
        throw new RuntimeException("Serviço de CEP indisponível");
    }
}
