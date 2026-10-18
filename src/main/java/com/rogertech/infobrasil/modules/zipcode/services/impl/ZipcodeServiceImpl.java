package com.rogertech.infobrasil.modules.zipcode.services.impl;

import com.rogertech.infobrasil.modules.zipcode.clients.ViaCepClient;
import com.rogertech.infobrasil.modules.zipcode.exceptions.ZipcodeErrorType;
import com.rogertech.infobrasil.modules.zipcode.exceptions.ZipcodeNotFound;
import com.rogertech.infobrasil.modules.zipcode.model.AddressDTO;
import com.rogertech.infobrasil.modules.zipcode.model.AddressResponse;
import com.rogertech.infobrasil.modules.zipcode.model.ZipcodeMapper;
import com.rogertech.infobrasil.modules.zipcode.services.ZipcodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ZipcodeServiceImpl implements ZipcodeService {

    private final ViaCepClient viaCepClient;
    private final ZipcodeMapper zipcodeMapper;

    public AddressResponse getAddress(String zipcode) {
        log.info("Iniciando busca de endereço para o CEP: {}", zipcode);

        AddressDTO addressDTO = viaCepClient.getAddress(zipcode);

        if (addressDTO.isError()) {

            throw new ZipcodeNotFound(HttpStatus.NOT_FOUND.value(), ZipcodeErrorType.ZIPCODE_NOT_FOUND);
        }

        log.info("Endereço localizado com sucesso para o CEP: {}", zipcode);

        return zipcodeMapper.toResponse(addressDTO);
        //return AddressResponse.FromDTO(addressDTO); -- Retorno sem o mapper

    }
}
