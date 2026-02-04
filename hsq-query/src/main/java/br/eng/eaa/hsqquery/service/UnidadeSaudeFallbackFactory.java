package br.eng.eaa.hsqquery.service;

import br.eng.eaa.hsqquery.exception.UnidadeSaudeException;
import br.eng.eaa.hsqquery.model.UnidadeSaudeDTO;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnidadeSaudeFallbackFactory implements FallbackFactory<UnidadeSaudeClient> {

    @Override
    public UnidadeSaudeClient create(Throwable cause) {

        System.err.println("Fallback acionado por: " + cause.getMessage());

        return new UnidadeSaudeClient() {
            @Override
            public List<UnidadeSaudeDTO> listUnidadesSaude(String especialidade) {
                throw new UnidadeSaudeException("Sistema indisponível no momento: " + cause.getMessage());
            }

            @Override
            public List<UnidadeSaudeDTO> findAll() {
                throw new UnidadeSaudeException("Sistema indisponível no momento: " + cause.getMessage());
            }
        };
    }
}
