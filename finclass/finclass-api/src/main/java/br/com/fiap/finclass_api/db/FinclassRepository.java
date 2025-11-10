package br.com.fiap.finclass_api.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Map;

@Repository
public class FinclassRepository {

  private final JdbcTemplate jdbc;

  public FinclassRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  /* ========= DESPESAS ========= */

  /** pr_despesa_ins(OUT p_id, p_desc, p_categoria, p_valor, p_data, p_usuario) */
  public long inserirDespesa(String desc, String categoria, BigDecimal valor, Date data, long usuarioId) {
    return jdbc.execute((Connection con) -> {
      try (CallableStatement cs = con.prepareCall("{ call pr_despesa_ins(?, ?, ?, ?, ?, ?) }")) {
        cs.registerOutParameter(1, Types.NUMERIC); // p_id OUT
        cs.setString(2, desc);
        cs.setString(3, categoria);
        cs.setBigDecimal(4, valor);
        cs.setDate(5, data);
        cs.setLong(6, usuarioId);
        cs.execute();
        return cs.getLong(1);
      }
    });
  }

  /** pr_despesa_upd(p_id, p_desc, p_categoria, p_valor, p_data) — passe null para manter campo */
  public void atualizarDespesa(long id, String desc, String categoria, BigDecimal valor, Date data) {
    jdbc.execute((Connection con) -> {
      try (CallableStatement cs = con.prepareCall("{ call pr_despesa_upd(?, ?, ?, ?, ?) }")) {
        cs.setLong(1, id);
        cs.setString(2, desc);
        cs.setString(3, categoria);
        cs.setBigDecimal(4, valor);
        cs.setDate(5, data);
        cs.execute();
        return null;
      }
    });
  }

  /** pr_despesa_del(p_id) */
  public void deletarDespesa(long id) {
    jdbc.execute((Connection con) -> {
      try (CallableStatement cs = con.prepareCall("{ call pr_despesa_del(?) }")) {
        cs.setLong(1, id);
        cs.execute();
        return null;
      }
    });
  }

  /* ========= METAS ========= */

  /** pr_meta_ins(OUT p_id, p_nome, p_objetivo, p_atual, p_prazo, p_usuario) */
  public long inserirMeta(String nome, BigDecimal objetivo, BigDecimal atual, Date prazo, long usuarioId) {
    return jdbc.execute((Connection con) -> {
      try (CallableStatement cs = con.prepareCall("{ call pr_meta_ins(?, ?, ?, ?, ?, ?) }")) {
        cs.registerOutParameter(1, Types.NUMERIC);
        cs.setString(2, nome);
        cs.setBigDecimal(3, objetivo);
        cs.setBigDecimal(4, atual);
        cs.setDate(5, prazo);
        cs.setLong(6, usuarioId);
        cs.execute();
        return cs.getLong(1);
      }
    });
  }

  /** pr_meta_upd(p_id, p_nome, p_objetivo, p_atual, p_prazo) — passe null para manter campo */
  public void atualizarMeta(long id, String nome, BigDecimal objetivo, BigDecimal atual, Date prazo) {
    jdbc.execute((Connection con) -> {
      try (CallableStatement cs = con.prepareCall("{ call pr_meta_upd(?, ?, ?, ?, ?) }")) {
        cs.setLong(1, id);
        cs.setString(2, nome);
        cs.setBigDecimal(3, objetivo);
        cs.setBigDecimal(4, atual);
        cs.setDate(5, prazo);
        cs.execute();
        return null;
      }
    });
  }

  /** pr_meta_del(p_id) */
  public void deletarMeta(long id) {
    jdbc.execute((Connection con) -> {
      try (CallableStatement cs = con.prepareCall("{ call pr_meta_del(?) }")) {
        cs.setLong(1, id);
        cs.execute();
        return null;
      }
    });
  }

  /* ========= RELATÓRIOS (table functions) ========= */

  /** SELECT * FROM TABLE(fn_relatorio_despesas(?)) — se usuarioId == null, passa NULL */
  public List<Map<String, Object>> relatorioDespesas(Long usuarioId) {
    if (usuarioId == null) {
      return jdbc.queryForList("SELECT * FROM TABLE(fn_relatorio_despesas(NULL))");
    }
    return jdbc.queryForList("SELECT * FROM TABLE(fn_relatorio_despesas(?))", usuarioId);
  }

  /** SELECT * FROM TABLE(fn_relatorio_metas()) */
  public List<Map<String, Object>> relatorioMetas() {
    return jdbc.queryForList("SELECT * FROM TABLE(fn_relatorio_metas())");
  }
}
