package com.rogertech.infobrasil.modules.zipcode.clients;

import com.rogertech.infobrasil.modules.zipcode.model.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws", fallback = ViaCepFallback.class)
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    AddressDTO getAddress(@PathVariable("cep") String cep);
}
