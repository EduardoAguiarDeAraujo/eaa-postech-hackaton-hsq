package br.eng.eaa.hsqquery.controller.handler;

import br.eng.eaa.hsqquery.exception.AtendimentoException;
import br.eng.eaa.hsqquery.exception.UnidadeSaudeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UnidadeSaudeException.class)
    public ResponseEntity<String> handleUnidadeSaudeException(UnidadeSaudeException e) {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(e.getMessage());
    }

    @ExceptionHandler(AtendimentoException.class)
    public ResponseEntity<String> handleAtendimentoException(AtendimentoException e) {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(e.getMessage());
    }
}
