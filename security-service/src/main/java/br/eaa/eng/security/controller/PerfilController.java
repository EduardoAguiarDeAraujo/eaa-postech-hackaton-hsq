package br.eaa.eng.security.controller;

import br.eaa.eng.security.model.Perfil;
import br.eaa.eng.security.service.PerfilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> findAll(){
        return ResponseEntity.ok(perfilService.findAll());
    }
}
