package br.eng.eaa.hsqcommand.message;

import br.eng.eaa.hsqcommand.config.RabbitConfig;
import br.eng.eaa.hsqcommand.model.AgendamentoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoProducer {

    private final RabbitTemplate rabbitTemplate;

    public AgendamentoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(AgendamentoDTO agendamento) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_AGENDAMENTO,"", agendamento);
        System.out.println("Enviado para a fila: " + agendamento);
    }
}
