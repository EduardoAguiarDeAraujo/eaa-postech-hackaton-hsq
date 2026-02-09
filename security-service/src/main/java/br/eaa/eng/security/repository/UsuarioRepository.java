package br.eaa.eng.security.repository;

import br.eaa.eng.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    UserDetails findByUsername(String username);

    Optional<Usuario> findUsuarioByUsername(String username);

}
