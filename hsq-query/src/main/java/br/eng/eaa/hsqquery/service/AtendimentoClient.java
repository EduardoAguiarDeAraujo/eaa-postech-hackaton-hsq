package br.eng.eaa.hsqquery.service;

import br.eng.eaa.hsqquery.model.AtendimentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "rnds-atendimento", url = "http://localhost:8582", fallbackFactory = AtendimentoFallbackFactory.class)
public interface AtendimentoClient {

    @GetMapping("/api/v1/atendimentos")
    List<AtendimentoDTO> listarAtendimentos();


    @GetMapping("/api/v1/atendimentos/cnes/{cnes}")
    List<AtendimentoDTO> listarAtendimentosPorCnes(@PathVariable("cnes") String cnes);

}
