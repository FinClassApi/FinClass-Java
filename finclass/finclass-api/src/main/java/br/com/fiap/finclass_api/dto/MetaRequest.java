package br.com.fiap.finclass_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MetaRequest(
        @NotBlank String nome,
        @NotNull @Positive BigDecimal valorObjetivo,
        @NotNull @Positive BigDecimal valorAtual,
        @NotNull LocalDate prazo,
        @NotNull Long usuarioId
) { }
