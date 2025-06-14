package br.com.base_rest_api.domain.dto.request.autenticacao;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull(message = "CPF é obrigatório")
        String cpf,
        @NotNull(message = "Senha é obrigatória")
        String senha
) {
}