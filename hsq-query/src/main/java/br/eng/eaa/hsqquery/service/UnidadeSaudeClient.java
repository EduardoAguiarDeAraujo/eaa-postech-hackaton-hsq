package br.eng.eaa.hsqquery.service;

import br.eng.eaa.hsqquery.model.UnidadeSaudeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cnes-unidade", url = "http://localhost:8581", fallbackFactory = UnidadeSaudeFallbackFactory.class)
public interface UnidadeSaudeClient {

    @GetMapping("/api/v1/unidades/{especialidade}")
    List<UnidadeSaudeDTO> listUnidadesSaude(@PathVariable("especialidade") String especialidade);


    @GetMapping("/api/v1/unidades")
    List<UnidadeSaudeDTO> findAll();

}
