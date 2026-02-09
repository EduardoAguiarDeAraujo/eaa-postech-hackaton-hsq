package br.eng.eaa.hsqcommand.controller.handler;

import br.eng.eaa.hsqcommand.exception.AgendamentoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AgendamentoException.class)
    public ResponseEntity<String> handleAgendamentoException(AgendamentoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
