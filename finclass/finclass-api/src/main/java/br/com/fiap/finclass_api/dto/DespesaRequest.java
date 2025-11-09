package br.com.fiap.finclass_api.dto;

import br.com.fiap.finclass_api.enums.CategoriaDespesa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaRequest(
        @NotBlank String descricao,
        @NotNull CategoriaDespesa categoria,
        @NotNull @Positive BigDecimal valor,
        @NotNull LocalDate data,
        @NotNull Long usuarioId
) { }
