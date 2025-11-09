package br.com.fiap.finclass_api.dto;

public record LoginResponse(
        Long usuarioId,
        String nome,
        String email,
        String mensagem
) { }
