package br.eng.eaa.hsqcommand.service;

import br.eng.eaa.hsqcommand.exception.AgendamentoException;
import br.eng.eaa.hsqcommand.mapper.AgendamentoMapper;
import br.eng.eaa.hsqcommand.message.AgendamentoProducer;
import br.eng.eaa.hsqcommand.model.Agendamento;
import br.eng.eaa.hsqcommand.model.AgendamentoDTO;
import br.eng.eaa.hsqcommand.model.StatusAgendamento;
import br.eng.eaa.hsqcommand.repository.AgendamentoRepository;
import br.eng.eaa.hsqcommand.repository.EspecialidadeRepository;
import br.eng.eaa.hsqcommand.repository.PacienteRepository;
import br.eng.eaa.hsqcommand.repository.UnidadeSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AgendamentoService {

    @Autowired private AgendamentoRepository repository;
    @Autowired private PacienteRepository pacienteRepository;
    @Autowired private EspecialidadeRepository especialidadeRepository;
    @Autowired private UnidadeSaudeRepository unidadeRepository;


    private AgendamentoProducer agendamentoProducer;

    public AgendamentoService(AgendamentoProducer agendamentoProducer) {
        this.agendamentoProducer = agendamentoProducer;
    }

    public AgendamentoDTO create(AgendamentoDTO dto) {
        try {
            agendamentoProducer.send(dto);
        } catch (Exception e){
            throw new AgendamentoException(e.getMessage());
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setPaciente(pacienteRepository.getReferenceById(dto.pacienteId()));
        agendamento.setEspecialidade(especialidadeRepository.getReferenceById(dto.especialidadeId()));
        if (dto.unidadeSaudeId() != null) {
            agendamento.setUnidadeSaude(unidadeRepository.getReferenceById(dto.unidadeSaudeId()));
        }
        agendamento.setDataSolicitacao(dto.dataSolicitacao() != null ? dto.dataSolicitacao() : LocalDateTime.now());
        agendamento.setDataAgendamento(dto.dataAgemdamento());
        agendamento.setJustificativaMedica(dto.justificativaMedica());
        agendamento.setStatus(dto.status() != null ? StatusAgendamento.valueOf(dto.status()) : StatusAgendamento.AGENDADO);

        Agendamento salvo = repository.save(agendamento);

        return AgendamentoMapper.toDTO(salvo);

    }
}
