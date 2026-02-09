package br.eng.eaa.hsqquery.repository;

import br.eng.eaa.hsqquery.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByCpf(String cpf);
    Optional<Paciente> findByCns(String cns);
    List<Paciente> findByNomeContainingIgnoreCase(String nome);

}
