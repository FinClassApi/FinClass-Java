package br.com.fiap.finclass_api.dto;

import br.com.fiap.finclass_api.enums.CategoriaDespesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaResponse(
        Long id,
        String descricao,
        CategoriaDespesa categoria,
        BigDecimal valor,
        LocalDate data,
        Long usuarioId
) { }
