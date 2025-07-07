package br.com.base_rest_api.domain.dto.response.cliente;

import br.com.base_rest_api.domain.enums.SituacaoEnum;
import br.com.base_rest_api.domain.enums.TipoPessoaEnum;

public record ClientePesquisaResponse(
        Long idCliente,
        TipoPessoaEnum tipoPessoa,
        String cpfCnpj,
        String nomeRazaoSocial,
        SituacaoEnum situacao
) {
}