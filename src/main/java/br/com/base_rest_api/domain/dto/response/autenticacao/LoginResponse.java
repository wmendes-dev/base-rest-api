package br.com.base_rest_api.domain.dto.response.autenticacao;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        long accessTokenExp,
        long refreshTokenExp,
        UsuarioResponse usuario
) {
}