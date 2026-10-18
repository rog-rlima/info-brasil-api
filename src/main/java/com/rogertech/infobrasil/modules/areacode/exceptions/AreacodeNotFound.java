package com.rogertech.infobrasil.modules.areacode.exceptions;

import com.rogertech.infobrasil.shards.exceptions.BaseBrasilException;

public class AreacodeNotFound extends BaseBrasilException {
    public AreacodeNotFound(int status, AreacodeErrorType areacodeErrorType) {
        super(status, areacodeErrorType.name(), areacodeErrorType.getMessage());
    }

    public AreacodeNotFound(int status, String type, String message) {
        super(status, type, message);
    }

}
