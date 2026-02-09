package br.eng.eaa.schedulingservice.message;

import br.eng.eaa.schedulingservice.config.RabbitConfig;
import br.eng.eaa.schedulingservice.model.AgendamentoDTO;
import br.eng.eaa.schedulingservice.service.AgendamentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoConsumer {

    private final AgendamentoService agendamentoService;

    public AgendamentoConsumer(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_SCHEDULING)
    public void receive(AgendamentoDTO agendamentoDTO) {
        agendamentoService.agendarConsulta(agendamentoDTO);
    }
}
