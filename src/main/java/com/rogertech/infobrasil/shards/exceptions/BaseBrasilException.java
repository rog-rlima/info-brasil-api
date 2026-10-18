package com.rogertech.infobrasil.shards.exceptions;

import lombok.Getter;

@Getter
public abstract class BaseBrasilException extends RuntimeException {

    private final int status;
    private final String type;

    public BaseBrasilException(int status, String type, String message) {
        super(message);
        this.type = type;
        this.status = status;
    }

}
