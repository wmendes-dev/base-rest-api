package br.com.base_rest_api.domain.dto.response.cliente;

import br.com.base_rest_api.domain.enums.SituacaoEnum;

public record ClientePesquisaResponse(
        Long idCliente,
        String nome,
        String cpf,
        SituacaoEnum situacao
) {
}