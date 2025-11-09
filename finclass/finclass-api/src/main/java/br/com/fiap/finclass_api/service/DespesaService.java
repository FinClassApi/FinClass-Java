package br.com.fiap.finclass_api.service;

import br.com.fiap.finclass_api.dto.DespesaRequest;
import br.com.fiap.finclass_api.dto.DespesaResponse;
import br.com.fiap.finclass_api.model.Despesa;
import br.com.fiap.finclass_api.model.Usuario;
import br.com.fiap.finclass_api.repository.DespesaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final UsuarioService usuarioService;

    public DespesaService(DespesaRepository despesaRepository, UsuarioService usuarioService) {
        this.despesaRepository = despesaRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public DespesaResponse criar(DespesaRequest request) {
        Usuario usuario = usuarioService.buscarPorId(request.usuarioId());

        Despesa despesa = Despesa.builder()
                .descricao(request.descricao())
                .categoria(request.categoria())
                .valor(request.valor())
                .data(request.data())
                .usuario(usuario)
                .build();

        despesa = despesaRepository.save(despesa);

        return toResponse(despesa);
    }

    @Transactional(readOnly = true)
    public List<DespesaResponse> listarPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        return despesaRepository.findByUsuario(usuario)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private DespesaResponse toResponse(Despesa despesa) {
        return new DespesaResponse(
                despesa.getId(),
                despesa.getDescricao(),
                despesa.getCategoria(),
                despesa.getValor(),
                despesa.getData(),
                despesa.getUsuario().getId()
        );
    }
}
