package br.com.fiap.finclass_api;

import br.com.fiap.finclass_api.db.FinclassRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Component
public class DemoRunner implements CommandLineRunner {

  private final FinclassRepository repo;
  public DemoRunner(FinclassRepository repo) { this.repo = repo; }

  @Override
  public void run(String... args) {
    long usuarioId = 1L; // use um ID REAL que exista na tabela USUARIOS

    // ===== DESPESAS: 2 INSERTs =====
    long d1 = repo.inserirDespesa("Mercado", "ALIMENTACAO", new BigDecimal("120.00"),
                                  Date.valueOf(LocalDate.now()), usuarioId);
    long d2 = repo.inserirDespesa("Uber", "TRANSPORTE", new BigDecimal("35.50"),
                                  Date.valueOf(LocalDate.now().minusDays(1)), usuarioId);

    // ===== DESPESAS: 2 UPDATEs =====
    repo.atualizarDespesa(d1, "Mercado (ajuste)", null, new BigDecimal("150.00"), null);
    repo.atualizarDespesa(d2, null, "TRANSPORTE", new BigDecimal("40.00"), null);

    // ===== DESPESAS: 2 DELETEs =====
    repo.deletarDespesa(d2);
    repo.deletarDespesa(d1);

    // ===== METAS: 2 INSERTs =====
    long m1 = repo.inserirMeta("Reserva", new BigDecimal("2000"), new BigDecimal("500"),
                               Date.valueOf(LocalDate.now().plusMonths(3)), usuarioId);
    long m2 = repo.inserirMeta("Notebook", new BigDecimal("6000"), new BigDecimal("1000"),
                               Date.valueOf(LocalDate.now().plusMonths(6)), usuarioId);

    // ===== METAS: 2 UPDATEs =====
    repo.atualizarMeta(m1, "Reserva (ajuste)", new BigDecimal("2500"), new BigDecimal("750"), null);
    repo.atualizarMeta(m2, null, null, new BigDecimal("1500"), null);

    // ===== METAS: 2 DELETEs =====
    repo.deletarMeta(m2);
    repo.deletarMeta(m1);

    // ===== RELATÓRIOS (table functions) =====
    System.out.println("[Relatório] Despesas (amostra): " + repo.relatorioDespesas(null));
    System.out.println("[Relatório] Metas (amostra): " + repo.relatorioMetas());
  }
}
