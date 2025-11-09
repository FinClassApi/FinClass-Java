package br.com.fiap.finclass_api.dto;

import java.math.BigDecimal;

public record ResumoFinanceiroResponse(
        Long usuarioId,
        BigDecimal totalDespesas,
        BigDecimal totalMetas,
        BigDecimal saldoProjetado
) { }
