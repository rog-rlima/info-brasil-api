package com.rogertech.infobrasil.shards.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogertech.infobrasil.shards.exceptions.model.BrasilApiError;
import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(BaseBrasilException.class)
    public ResponseEntity<Object> handleBase(BaseBrasilException e) {
        log.error("Erro na base Brasil: status={}, type={}, message={}", e.getStatus(), e.getType(), e.getMessage());
        ResponseException responseException = new ResponseException(e.getStatus(), e.getType(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(responseException);
    }


    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ResponseException> handleFeignNotFound(FeignException e) throws JsonProcessingException {
        String type = "";
        String message = "";

        try {
            // Tenta deserializar a mensagem interna da BrasilAPI
            BrasilApiError brasilApiError = BrasilApiError.FromDTO(e.getMessage(), objectMapper);
            type = brasilApiError.name();
            message = brasilApiError.message();

        } catch (Exception ex) {
            log.warn("Não foi possível parsear o JSON de erro do Feign. Usando valores padrão. Erro: {}", ex.getMessage());
        }

        ResponseException response = new ResponseException(e.status(), type, message);
        return ResponseEntity.status(e.status()).body(response);
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<ResponseException> handleRetryableException(RetryableException e) {
        int status = HttpStatus.BAD_GATEWAY.value(); // Alterado de BAD_REQUEST para BAD_GATEWAY
        String type = "INTEGRATION_TIMEOUT";

        log.error("Timeout ou indisponibilidade na API externa: {}", e.getMessage());

        ResponseException response = new ResponseException(status, type, "Servidor externo temporariamente indisponível.");
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> handleGenericException(Exception e) {
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String type = "INTERNAL_SERVER_ERROR";

        log.error("Erro interno não mapeado no sistema: ", e);

        ResponseException response = new ResponseException(status, type, "Ocorreu um erro interno inesperado.");
        return ResponseEntity.status(status).body(response);
    }

}
