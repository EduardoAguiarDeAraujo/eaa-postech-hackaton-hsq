package br.eng.eaa.hsqquery.controller;

import br.eng.eaa.hsqquery.model.AgendamentoResponseDTO;
import br.eng.eaa.hsqquery.service.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarAgendamentosPorPaciente(@PathVariable("cpf") String cpf){
        List<AgendamentoResponseDTO> agendamentos = service.listarPorCPF(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(agendamentos);
    }
}
