package br.com.fiap.finclass_api.service;

import br.com.fiap.finclass_api.dto.ResumoFinanceiroResponse;
import br.com.fiap.finclass_api.model.Despesa;
import br.com.fiap.finclass_api.model.MetaFinanceira;
import br.com.fiap.finclass_api.model.Usuario;
import br.com.fiap.finclass_api.repository.DespesaRepository;
import br.com.fiap.finclass_api.repository.MetaFinanceiraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RelatorioService {

    private final DespesaRepository despesaRepository;
    private final MetaFinanceiraRepository metaRepository;
    private final UsuarioService usuarioService;

    public RelatorioService(DespesaRepository despesaRepository,
                            MetaFinanceiraRepository metaRepository,
                            UsuarioService usuarioService) {
        this.despesaRepository = despesaRepository;
        this.metaRepository = metaRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public ResumoFinanceiroResponse gerarResumo(Long usuarioId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        List<Despesa> despesas = despesaRepository.findByUsuario(usuario);
        List<MetaFinanceira> metas = metaRepository.findByUsuario(usuario);

        BigDecimal totalDespesas = despesas.stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalMetas = metas.stream()
                .map(MetaFinanceira::getValorObjetivo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal saldoProjetado = totalMetas.subtract(totalDespesas);

        return new ResumoFinanceiroResponse(
                usuario.getId(),
                totalDespesas,
                totalMetas,
                saldoProjetado
        );
    }
}
