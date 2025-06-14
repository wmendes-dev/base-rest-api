package br.com.base_rest_api.domain.dto.response.cliente;

public record ClienteResponse(
        Long idCliente,
        String nome,
        String cpf
) {
}