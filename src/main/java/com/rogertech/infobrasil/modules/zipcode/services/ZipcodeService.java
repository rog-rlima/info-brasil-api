package com.rogertech.infobrasil.modules.zipcode.services;

import com.rogertech.infobrasil.modules.zipcode.model.AddressDTO;
import com.rogertech.infobrasil.modules.zipcode.model.AddressResponse;

public interface ZipcodeService {
    AddressResponse getAddress(String zipcode);
}
