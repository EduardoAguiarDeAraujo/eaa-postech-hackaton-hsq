package br.eng.eaa.hsqquery.service;

import br.eng.eaa.hsqquery.mapper.AgendamentoMapper;
import br.eng.eaa.hsqquery.model.Agendamento;
import br.eng.eaa.hsqquery.model.AgendamentoDTO;
import br.eng.eaa.hsqquery.model.AgendamentoResponseDTO;
import br.eng.eaa.hsqquery.model.StatusAgendamento;
import br.eng.eaa.hsqquery.repository.AgendamentoRepository;
import br.eng.eaa.hsqquery.repository.EspecialidadeRepository;
import br.eng.eaa.hsqquery.repository.PacienteRepository;
import br.eng.eaa.hsqquery.repository.UnidadeSaudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired private AgendamentoRepository repository;
    @Autowired private PacienteRepository pacienteRepository;
    @Autowired private EspecialidadeRepository especialidadeRepository;
    @Autowired private UnidadeSaudeRepository unidadeRepository;

    @Transactional
    public AgendamentoDTO criarAgendamento(AgendamentoDTO dto) {

        Agendamento agendamento = new Agendamento();
        agendamento.setPaciente(pacienteRepository.getReferenceById(dto.pacienteId()));
        agendamento.setEspecialidade(especialidadeRepository.getReferenceById(dto.especialidadeId()));
        if (dto.unidadeSaudeId() != null) {
            agendamento.setUnidadeSaude(unidadeRepository.getReferenceById(dto.unidadeSaudeId()));
        }
        agendamento.setDataSolicitacao(dto.dataSolicitacao() != null ? dto.dataSolicitacao() : LocalDateTime.now());
        agendamento.setDataAgendamento(dto.dataAgemdamento());
        agendamento.setJustificativaMedica(dto.justificativaMedica());
        agendamento.setStatus(dto.status() != null ? StatusAgendamento.valueOf(dto.status()) : StatusAgendamento.PENDENTE);

        Agendamento salvo = repository.save(agendamento);

        return AgendamentoMapper.toAgendamentoDTO(salvo);
    }

    public List<AgendamentoResponseDTO> listarPorCPF(String cpf) {
        return repository.findByPacienteCpf(cpf).stream()
                .map(AgendamentoMapper::toAgendamentoResponseDTO)
                .collect(Collectors.toList());
    }



}
