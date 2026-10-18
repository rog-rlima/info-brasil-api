package com.rogertech.infobrasil.shards.exceptions;

public record ResponseException(
        int status,
        String type,
        String message
) {
}
