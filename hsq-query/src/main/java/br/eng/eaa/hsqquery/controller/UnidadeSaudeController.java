package br.eng.eaa.hsqquery.controller;

import br.eng.eaa.hsqquery.model.SugestaoUnidadeDTO;
import br.eng.eaa.hsqquery.model.UnidadeSaudeDTO;
import br.eng.eaa.hsqquery.service.UnidadeSaudeClient;
import br.eng.eaa.hsqquery.service.UnidadeSaudeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/unidades")
@SecurityRequirement(name = "bearerAuth")
public class UnidadeSaudeController {

    private final UnidadeSaudeClient unidadeSaudeClient;
    private final UnidadeSaudeService unidadeSaudeService;

    public UnidadeSaudeController(UnidadeSaudeClient unidadeSaudeClient, UnidadeSaudeService unidadeSaudeService) {
        this.unidadeSaudeClient = unidadeSaudeClient;
        this.unidadeSaudeService = unidadeSaudeService;
    }

    @Operation(summary = "Listar unidades por especialidade")
    @GetMapping("/{especialidade}")
    public ResponseEntity<List<UnidadeSaudeDTO> > listUnidadesSaude(@PathVariable("especialidade") String especialidade){
        List<UnidadeSaudeDTO> unidades = unidadeSaudeService.listarPorEspecialidade(especialidade);
        return ResponseEntity.status(HttpStatus.OK).body(unidades);
    }

    @Operation(summary = "Listar unidades por CEP")
    @GetMapping("/cep/{cep}")
    public ResponseEntity<List<UnidadeSaudeDTO>> listarUnidadesPorCep(@PathVariable("cep") String cep){
        List<UnidadeSaudeDTO> unidades = unidadeSaudeService.listarPorCEP(cep);
        return ResponseEntity.status(HttpStatus.OK).body(unidades);
    }

    @Operation(summary = "Listar por especialidade e CEP")
    @GetMapping("/especialidade/{especialidade}/cep/{cep}")
    public ResponseEntity<List<UnidadeSaudeDTO>> listarUnidadesPorCep(@PathVariable("especialidade") String especialidade, @PathVariable("cep") String cep){
        List<UnidadeSaudeDTO> unidades = unidadeSaudeService.listarPorEspecialidadeECEP(especialidade, cep);
        return ResponseEntity.status(HttpStatus.OK).body(unidades);
    }

    @Operation(summary = "Listar todas as unidades cadastradas")
    @GetMapping()
    public ResponseEntity<List<UnidadeSaudeDTO>> findAll(){
        List<UnidadeSaudeDTO> unidades = unidadeSaudeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(unidades);
    }

    @Operation(summary = "Sugerir unidades por menor ocupação", description = "Retorna unidades filtradas por especialidade e CEP, ordenadas pela menor taxa de ocupação.")
    @GetMapping("/ocupacao/{especialidade}/{cep}")
    public ResponseEntity<List<SugestaoUnidadeDTO>> sugestaoUnidadePorOcupacao(@PathVariable("especialidade") String especialidade, @PathVariable("cep") String cep){
        List<SugestaoUnidadeDTO> sugestoes = unidadeSaudeService.sugestaoUnidadesPorOcupacao(especialidade,cep);
        return ResponseEntity.status(HttpStatus.OK).body(sugestoes);
    }

}
