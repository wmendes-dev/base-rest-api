package br.com.base_rest_api.domain.dto.response.autenticacao;

public record UsuarioResponse(
        Long idUsuario,
        String cpf,
        String nomeCompleto
) {
}