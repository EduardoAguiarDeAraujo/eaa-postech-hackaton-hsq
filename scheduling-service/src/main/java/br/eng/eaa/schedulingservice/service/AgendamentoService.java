package br.eng.eaa.schedulingservice.service;

import br.eng.eaa.schedulingservice.message.EmailProducer;
import br.eng.eaa.schedulingservice.model.AgendamentoDTO;
import br.eng.eaa.schedulingservice.model.EmailMessageDTO;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    private final AgendamentoClient agendamentoClient;
    private final EmailProducer emailProducer;

    public AgendamentoService(AgendamentoClient agendamentoClient, EmailProducer emailProducer) {
        this.agendamentoClient = agendamentoClient;
        this.emailProducer = emailProducer;
    }

    public AgendamentoDTO agendarConsulta(AgendamentoDTO agendamento) {
        AgendamentoDTO agendamentoRealizado = agendamentoClient.agendarConsulta(agendamento);
        String body = "Enviando para SISREG: " + agendamentoRealizado.justificativaMedica();
        emailProducer.sendEmail(new EmailMessageDTO("paciente@gmail.com","Confirmação de agendamento", body));
        return agendamentoRealizado;
    }
}
