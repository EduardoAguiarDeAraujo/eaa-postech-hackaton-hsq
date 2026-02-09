package br.eng.eaa.hsqquery.message;

import br.eng.eaa.hsqquery.config.RabbitConfig;
import br.eng.eaa.hsqquery.model.AgendamentoDTO;
import br.eng.eaa.hsqquery.service.AgendamentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoConsumer {

    private final AgendamentoService agendamentoService;

    public AgendamentoConsumer(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_HSQ_QUERY)
    public void receive(AgendamentoDTO agendamentoDTO) {
        agendamentoService.criarAgendamento(agendamentoDTO);
    }
}
