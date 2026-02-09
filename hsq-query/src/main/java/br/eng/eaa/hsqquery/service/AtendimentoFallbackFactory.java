package br.eng.eaa.hsqquery.service;

import br.eng.eaa.hsqquery.exception.AtendimentoException;
import br.eng.eaa.hsqquery.model.AtendimentoDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtendimentoFallbackFactory implements FallbackFactory<AtendimentoClient> {

    @Override
    public AtendimentoClient create(Throwable cause) {

        System.err.println("Fallback acionado por: " + cause.getMessage());

        return new AtendimentoClient() {
            @Override
            public List<AtendimentoDTO> listarAtendimentos() {
                throw new AtendimentoException("Sistema indisponível no momento: " + cause.getMessage());
            }

            @Override
            public List<AtendimentoDTO> listarAtendimentosPorCnes(String cnes) {
                throw new AtendimentoException("Sistema indisponível no momento: " + cause.getMessage());
            }
        };
    }
}
