package com.rogertech.infobrasil.modules.areacode.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AreacodeErrorType {
    AREACODE_NOT_FOUND("O DDD informado não foi encontrado.");

    private final String message;
}
