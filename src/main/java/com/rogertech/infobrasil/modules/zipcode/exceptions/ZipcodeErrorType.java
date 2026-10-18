package com.rogertech.infobrasil.modules.zipcode.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ZipcodeErrorType {
    ZIPCODE_NOT_FOUND("O CEP informado não foi encontrado."),
    EXTERNAL_SERVICE_ERROR("Erro ao comunicar com o serviço externo de CEP.");

    private final String message;
}