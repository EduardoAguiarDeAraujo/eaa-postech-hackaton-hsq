package br.eng.eaa.hsqcommand.repository;

import br.eng.eaa.hsqcommand.model.UnidadeSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, Long> {

    Optional<UnidadeSaude> findByCnes(String cnes);
    List<UnidadeSaude> findByEnderecoCidadeIgnoreCase(String cidade);
    List<UnidadeSaude> findByEspecialidadesNomeIgnoreCase(String nomeEspecialidade);

}
