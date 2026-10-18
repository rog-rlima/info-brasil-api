package com.rogertech.infobrasil.shards.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class MdcFilter extends OncePerRequestFilter {

    private static final String TRACE_ID_KEY = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // 1. Verifica se a requisição já veio com um ID de rastreio de fora (ex: de um API Gateway)
            String traceId = request.getHeader("X-Trace-Id");

            if (traceId == null || traceId.isBlank()) {
                // 2. Se não veio, gera um novo ID único para esta requisição
                traceId = UUID.randomUUID().toString();
            }

            // 3. Insere o ID no MDC do Logback
            MDC.put(TRACE_ID_KEY, traceId);

            // 4. Adiciona o ID na resposta HTTP para facilitar o suporte caso o usuário veja um erro
            response.addHeader("X-Trace-Id", traceId);

            // Continua a execução normal da requisição (vai para o Controller, Service, etc.)
            filterChain.doFilter(request, response);

        } finally {
            // 5. CRÍTICO: Limpa o MDC após o fim da requisição.
            // Como o Tomcat reutiliza threads, se não limparmos, a próxima requisição herdará o ID antigo.
            MDC.remove(TRACE_ID_KEY);
        }
    }
}
