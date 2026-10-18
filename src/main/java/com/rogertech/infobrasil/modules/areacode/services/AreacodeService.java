package com.rogertech.infobrasil.modules.areacode.services;

import com.rogertech.infobrasil.modules.areacode.model.AreacodeResponse;

public interface AreacodeService {
    AreacodeResponse getAreacode(String ddd);
}
