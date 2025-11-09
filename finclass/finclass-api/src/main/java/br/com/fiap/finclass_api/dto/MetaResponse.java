package br.com.fiap.finclass_api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MetaResponse(
        Long id,
        String nome,
        BigDecimal valorObjetivo,
        BigDecimal valorAtual,
        LocalDate prazo,
        Long usuarioId
) { }
