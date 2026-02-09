package br.eaa.eng.security.service;

import br.eaa.eng.security.model.Perfil;
import br.eaa.eng.security.repository.PerfilRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }
}
