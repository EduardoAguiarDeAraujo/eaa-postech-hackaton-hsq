package br.eng.eaa.hsqquery.controller;

import br.eng.eaa.hsqquery.model.AtendimentoDTO;
import br.eng.eaa.hsqquery.service.AtendimentoClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/atendimentos")
public class AtendimentoController {

    private final AtendimentoClient atendimentoClient;

    public AtendimentoController(AtendimentoClient atendimentoClient) {
        this.atendimentoClient = atendimentoClient;
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoDTO>> listarAtendimentos(){
        List<AtendimentoDTO> atendimentos = atendimentoClient.listarAtendimentos();
        return ResponseEntity.status(HttpStatus.OK).body(atendimentos);
    }

    @GetMapping("/{cnes}")
    public ResponseEntity<List<AtendimentoDTO>> listarAtendimentosPorCnes(@PathVariable("cnes") String cnes){
        List<AtendimentoDTO> atendimentos = atendimentoClient.listarAtendimentosPorCnes(cnes);
        return ResponseEntity.status(HttpStatus.OK).body(atendimentos);
    }

}
