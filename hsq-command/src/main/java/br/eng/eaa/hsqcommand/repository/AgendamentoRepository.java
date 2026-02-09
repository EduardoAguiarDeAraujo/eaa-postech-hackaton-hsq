package br.eng.eaa.hsqcommand.repository;

import br.eng.eaa.hsqcommand.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {


}
