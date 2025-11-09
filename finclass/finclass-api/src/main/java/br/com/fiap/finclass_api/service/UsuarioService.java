package br.com.fiap.finclass_api.service;

import br.com.fiap.finclass_api.dto.LoginRequest;
import br.com.fiap.finclass_api.dto.LoginResponse;
import br.com.fiap.finclass_api.exception.BusinessException;
import br.com.fiap.finclass_api.exception.ResourceNotFoundException;
import br.com.fiap.finclass_api.model.Usuario;
import br.com.fiap.finclass_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario registrar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new BusinessException("Já existe um usuário cadastrado com este e-mail.");
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public LoginResponse autenticar(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para o e-mail informado."));

        if (!usuario.getSenha().equals(request.senha())) {
            throw new BusinessException("Senha inválida.");
        }

        return new LoginResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                "Login realizado com sucesso (token de autenticação poderá ser implementado na Sprint 2)."
        );
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id " + id));
    }
}
