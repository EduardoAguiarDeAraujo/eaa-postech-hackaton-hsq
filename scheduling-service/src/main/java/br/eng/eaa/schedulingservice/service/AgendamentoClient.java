package br.eng.eaa.schedulingservice.service;

import br.eng.eaa.schedulingservice.model.AgendamentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "scheduling-service", url = "http://localhost:8583", fallbackFactory = AgendamentoFallbackFactory.class)
public interface AgendamentoClient {

    @PostMapping("/api/v1/agendamentos")
    AgendamentoDTO agendarConsulta(@RequestBody AgendamentoDTO agendamento);

}

