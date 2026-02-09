package br.eng.eaa.schedulingservice.service;

import br.eng.eaa.schedulingservice.exception.AgendamentoException;
import br.eng.eaa.schedulingservice.model.AgendamentoDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoFallbackFactory implements FallbackFactory<AgendamentoClient> {
    @Override
    public AgendamentoClient create(Throwable cause) {

        System.err.println("Fallback acionado por: " + cause.getMessage());

        return new AgendamentoClient() {
            @Override
            public AgendamentoDTO agendarConsulta(AgendamentoDTO agendamento) {
                throw new AgendamentoException("Sistema indisponível no momento: " + cause.getMessage());
            }
        };
    }
}
