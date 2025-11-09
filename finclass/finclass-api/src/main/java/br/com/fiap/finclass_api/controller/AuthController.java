package br.com.fiap.finclass_api.controller;

import br.com.fiap.finclass_api.dto.LoginRequest;
import br.com.fiap.finclass_api.dto.LoginResponse;
import br.com.fiap.finclass_api.model.Usuario;
import br.com.fiap.finclass_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario registrar(@RequestBody @Valid Usuario usuario) {
        return usuarioService.registrar(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = usuarioService.autenticar(request);
        return ResponseEntity.ok(response);
    }
}
