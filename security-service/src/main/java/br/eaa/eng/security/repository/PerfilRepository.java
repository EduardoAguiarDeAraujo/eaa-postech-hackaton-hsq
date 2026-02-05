package br.eaa.eng.security.repository;

import br.eaa.eng.security.model.Perfil;
import br.eaa.eng.security.model.enums.PerfilName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PerfilRepository extends JpaRepository<Perfil, UUID> {

    Perfil findByPerfilName(PerfilName perfilName);
}
