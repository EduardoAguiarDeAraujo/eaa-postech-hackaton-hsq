package br.eng.eaa.hsqcommand.repository;

import br.eng.eaa.hsqcommand.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
