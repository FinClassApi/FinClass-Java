package br.com.fiap.finclass_api.service;

import br.com.fiap.finclass_api.dto.MetaRequest;
import br.com.fiap.finclass_api.dto.MetaResponse;
import br.com.fiap.finclass_api.model.MetaFinanceira;
import br.com.fiap.finclass_api.model.Usuario;
import br.com.fiap.finclass_api.repository.MetaFinanceiraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MetaFinanceiraService {

    private final MetaFinanceiraRepository metaRepository;
    private final UsuarioService usuarioService;

    public MetaFinanceiraService(MetaFinanceiraRepository metaRepository, UsuarioService usuarioService) {
        this.metaRepository = metaRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public MetaResponse criar(MetaRequest request) {
        Usuario usuario = usuarioService.buscarPorId(request.usuarioId());

        MetaFinanceira meta = MetaFinanceira.builder()
                .nome(request.nome())
                .valorObjetivo(request.valorObjetivo())
                .valorAtual(request.valorAtual())
                .prazo(request.prazo())
                .usuario(usuario)
                .build();

        meta = metaRepository.save(meta);

        return toResponse(meta);
    }

    @Transactional(readOnly = true)
    public List<MetaResponse> listarPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        return metaRepository.findByUsuario(usuario)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private MetaResponse toResponse(MetaFinanceira meta) {
        return new MetaResponse(
                meta.getId(),
                meta.getNome(),
                meta.getValorObjetivo(),
                meta.getValorAtual(),
                meta.getPrazo(),
                meta.getUsuario().getId()
        );
    }
}
