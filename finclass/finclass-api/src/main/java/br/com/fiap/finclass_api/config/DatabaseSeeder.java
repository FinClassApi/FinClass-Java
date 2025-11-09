package br.com.fiap.finclass_api.config;

import br.com.fiap.finclass_api.enums.CategoriaDespesa;
import br.com.fiap.finclass_api.model.Despesa;
import br.com.fiap.finclass_api.model.MetaFinanceira;
import br.com.fiap.finclass_api.model.Usuario;
import br.com.fiap.finclass_api.repository.DespesaRepository;
import br.com.fiap.finclass_api.repository.MetaFinanceiraRepository;
import br.com.fiap.finclass_api.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Database seeder para ambiente de desenvolvimento.
 * Executa apenas quando o profile "dev" estiver ativo.
 * Limpa as tabelas relevantes e insere dados de demonstração (usuário Julia + algumas despesas/metas).
 */
@Component
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final DespesaRepository despesaRepository;
    private final MetaFinanceiraRepository metaRepository;

    public DatabaseSeeder(UsuarioRepository usuarioRepository,
                          DespesaRepository despesaRepository,
                          MetaFinanceiraRepository metaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.despesaRepository = despesaRepository;
        this.metaRepository = metaRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Limpa dados existentes (ordem: filhos -> pais)
        try { despesaRepository.deleteAll(); } catch (Exception ignored) {}
        try { metaRepository.deleteAll(); } catch (Exception ignored) {}
        try { usuarioRepository.deleteAll(); } catch (Exception ignored) {}

        // Cria usuário demo "Julia Silva"
        Usuario julia = Usuario.builder()
                .nome("Julia Silva")
                .email("julia.silva@example.com")
                .senha("senha000") // em produção, aplicar hash!
                .build();
        julia = usuarioRepository.save(julia);

        // Duas despesas de exemplo
        Despesa d1 = Despesa.builder()
                .descricao("Almoço - restaurante")
                .categoria(CategoriaDespesa.ALIMENTACAO)
                .valor(new BigDecimal("45.90"))
                .data(LocalDate.now().minusDays(10))
                .usuario(julia)
                .build();

        Despesa d2 = Despesa.builder()
                .descricao("Uber para trabalho")
                .categoria(CategoriaDespesa.TRANSPORTE)
                .valor(new BigDecimal("18.50"))
                .data(LocalDate.now().minusDays(3))
                .usuario(julia)
                .build();

        despesaRepository.save(d1);
        despesaRepository.save(d2);

        // Meta financeira de exemplo
        MetaFinanceira m1 = MetaFinanceira.builder()
                .nome("Reserva de emergência")
                .valorObjetivo(new BigDecimal("1200.00"))
                .valorAtual(new BigDecimal("300.00"))
                .prazo(LocalDate.now().plusMonths(6))
                .usuario(julia)
                .build();

        metaRepository.save(m1);
    }
}
