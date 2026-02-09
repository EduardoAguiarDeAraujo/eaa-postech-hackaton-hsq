package br.eng.eaa.hsqquery.repository;

import br.eng.eaa.hsqquery.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByPacienteCpf(String cpf);

}
