package br.com.base_rest_api.domain.dto.request.autenticacao;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
        @NotNull(message = "refreshToken é obrigatório")
        String refreshToken
) {
}