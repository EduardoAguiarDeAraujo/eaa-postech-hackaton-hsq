package br.eaa.eng.security.service;


import br.eaa.eng.security.model.Perfil;
import br.eaa.eng.security.model.Usuario;
import br.eaa.eng.security.model.dto.LoginDto;
import br.eaa.eng.security.model.enums.PerfilName;
import br.eaa.eng.security.repository.PerfilRepository;
import br.eaa.eng.security.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario salvar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario novoLogin(LoginDto login) {
        Usuario usuario = usuarioRepository.findUsuarioByUsername(login.username())
                .orElseGet(() -> {
                    Usuario novo = new Usuario();
                    novo.setUsername(login.username());
                    Perfil perfil = perfilRepository.findByPerfilName(PerfilName.ROLE_USER);
                    novo.setRoles(new ArrayList<>(List.of(perfil)));
                    return novo;
                });

        usuario.setPassword(passwordEncoder.encode(login.password()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username);
    }

}
