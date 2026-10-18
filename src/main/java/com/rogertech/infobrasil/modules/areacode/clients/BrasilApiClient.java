package com.rogertech.infobrasil.modules.areacode.clients;

import com.rogertech.infobrasil.modules.areacode.model.AreacodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "brasilddd", url = "https://brasilapi.com.br/api")
public interface BrasilApiClient {

    @GetMapping("/ddd/v1/{ddd}")
    AreacodeDTO getAreacode(@PathVariable("ddd") String ddd);

}
