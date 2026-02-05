package br.eaa.eng.security.controller;

import br.eaa.eng.security.model.Usuario;
import br.eaa.eng.security.model.dto.LoginDto;
import br.eaa.eng.security.model.dto.TokenDto;
import br.eaa.eng.security.service.TokenService;
import br.eaa.eng.security.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto){
        var usuarioAutenticado = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
        var authentication = authenticationManager.authenticate(usuarioAutenticado);
        var token = tokenService.getToken((Usuario) authentication.getPrincipal());
        TokenDto tokenDto = new TokenDto(token);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/novo")
    public ResponseEntity novoLogin(@RequestBody @Valid LoginDto loginDto){
        usuarioService.novoLogin(loginDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginDto);
    }

}
