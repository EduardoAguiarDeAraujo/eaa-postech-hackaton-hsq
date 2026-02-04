package br.eng.eaa.hsqcommand.controller;

import br.eng.eaa.hsqcommand.model.AgendamentoDTO;
import br.eng.eaa.hsqcommand.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping()
    public ResponseEntity<AgendamentoDTO> agendarConsulta(@RequestBody AgendamentoDTO agendamento){
        AgendamentoDTO agendamentoDTO = agendamentoService.create(agendamento);
        return ResponseEntity.ok(agendamentoDTO);
    }

}
