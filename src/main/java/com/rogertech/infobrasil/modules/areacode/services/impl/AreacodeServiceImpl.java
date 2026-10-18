package com.rogertech.infobrasil.modules.areacode.services.impl;

import com.rogertech.infobrasil.modules.areacode.clients.BrasilApiClient;
import com.rogertech.infobrasil.modules.areacode.model.AreacodeDTO;
import com.rogertech.infobrasil.modules.areacode.model.AreacodeMapper;
import com.rogertech.infobrasil.modules.areacode.model.AreacodeResponse;
import com.rogertech.infobrasil.modules.areacode.services.AreacodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AreacodeServiceImpl implements AreacodeService {

    private final BrasilApiClient brasilApiClient;
    private final AreacodeMapper areacodeMapper;

    public AreacodeResponse getAreacode(String ddd) {
            log.info("Iniciando busca de codigo de area para o ddd: {}", ddd);
            AreacodeDTO areacodeDTO = brasilApiClient.getAreacode(ddd);
            log.info("Codigo de area localizado com sucesso para o CEP: {}", ddd);
            return areacodeMapper.toResponse(areacodeDTO);
    }

}
