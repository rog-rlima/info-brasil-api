package com.rogertech.infobrasil.modules.zipcode.exceptions;

import com.rogertech.infobrasil.shards.exceptions.BaseBrasilException;

public class ZipcodeNotFound extends BaseBrasilException {

    public ZipcodeNotFound(int status, ZipcodeErrorType zipcodeErrorType) {
        super(status, zipcodeErrorType.name(), zipcodeErrorType.getMessage());
    }
}
