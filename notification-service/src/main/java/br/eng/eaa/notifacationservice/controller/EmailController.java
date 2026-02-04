package br.eng.eaa.notifacationservice.controller;

import br.eng.eaa.notifacationservice.config.RabbitConfig;
import br.eng.eaa.notifacationservice.model.EmailMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    private final RabbitTemplate rabbitTemplate;

    public EmailController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmailToQueue(@RequestBody EmailMessageDTO message) {
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_NAME, message);
        return ResponseEntity.ok("Mensagem enviada para a fila com sucesso!");
    }
}