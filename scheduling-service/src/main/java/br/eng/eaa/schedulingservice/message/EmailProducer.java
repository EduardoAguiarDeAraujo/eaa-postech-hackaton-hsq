package br.eng.eaa.schedulingservice.message;

import br.eng.eaa.schedulingservice.config.RabbitConfig;
import br.eng.eaa.schedulingservice.model.EmailMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmail(EmailMessageDTO message) {
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_EMAIL, message);
    }
}
