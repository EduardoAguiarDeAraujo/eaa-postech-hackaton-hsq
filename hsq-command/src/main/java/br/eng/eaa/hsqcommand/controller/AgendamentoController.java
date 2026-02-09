package br.eng.eaa.hsqcommand.controller;

import br.eng.eaa.hsqcommand.model.AgendamentoDTO;
import br.eng.eaa.hsqcommand.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agendamentos")
@SecurityRequirement(name = "bearerAuth")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @Operation(
            summary = "Criar um novo agendamento",
            description = "Envia uma solicitação de agendamento para a fila de processamento."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "403", description = "Token inválido ou expirado")
    })
    @PostMapping()
    public ResponseEntity<AgendamentoDTO> agendarConsulta(@RequestBody AgendamentoDTO agendamento){
        AgendamentoDTO agendamentoDTO = agendamentoService.create(agendamento);
        return ResponseEntity.ok(agendamentoDTO);
    }

}
