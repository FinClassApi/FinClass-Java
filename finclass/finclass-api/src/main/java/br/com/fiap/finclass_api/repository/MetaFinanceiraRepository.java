package br.com.fiap.finclass_api.repository;

import br.com.fiap.finclass_api.model.MetaFinanceira;
import br.com.fiap.finclass_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetaFinanceiraRepository extends JpaRepository<MetaFinanceira, Long> {

    List<MetaFinanceira> findByUsuario(Usuario usuario);
}
