package br.eaa.eng.security.controller;

import br.eaa.eng.security.model.Usuario;
import br.eaa.eng.security.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        Usuario usuarioNovo = usuarioService.salvar(usuario);
        return ResponseEntity.ok(usuarioNovo);
    }

}
