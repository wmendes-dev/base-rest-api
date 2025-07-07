package br.com.base_rest_api.domain.dto.response.cliente;

import br.com.base_rest_api.domain.enums.SituacaoEnum;
import br.com.base_rest_api.domain.enums.TipoPessoaEnum;

import java.util.List;

public record ClienteResponse(
        Long idCliente,
        TipoPessoaEnum tipoPessoa,
        String cpfCnpj,
        String nomeRazaoSocial,
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        List<ClienteContatoResponse> contatos,
        SituacaoEnum situacao
) {
}