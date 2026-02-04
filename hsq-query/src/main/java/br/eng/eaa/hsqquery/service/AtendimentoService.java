package br.eng.eaa.hsqquery.service;

import br.eng.eaa.hsqquery.model.AtendimentoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendimentoService {

    private final AtendimentoClient atendimentoClient;

    public AtendimentoService(AtendimentoClient atendimentoClient) {
        this.atendimentoClient = atendimentoClient;
    }

    public List<AtendimentoDTO> listarAtendimentos(){
        List<AtendimentoDTO> atendimentos = atendimentoClient.listarAtendimentos();
        return atendimentos;
    }

    public List<AtendimentoDTO> listarAtendimentosPorCnes(String cnes){
        List<AtendimentoDTO> atendimentos = atendimentoClient.listarAtendimentosPorCnes(cnes);
        return atendimentos;
    }




}
