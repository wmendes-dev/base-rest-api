package br.com.base_rest_api.domain.dto.request.cliente;

import br.com.base_rest_api.domain.enums.SituacaoEnum;
import br.com.base_rest_api.domain.enums.TipoPessoaEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClienteRequest(
        @NotNull(message = "Tipo é obrigatório")
        TipoPessoaEnum tipoPessoa,
        @NotBlank(message = "CPF é obrigatório")
        String cpfCnpj,
        @NotBlank(message = "Nome é obrigatório")
        String nomeRazaoSocial,
        @Column(name = "CEP")
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        List<ClienteContatoRequest> contatos,
        @NotNull(message = "Situação é obrigatória")
        SituacaoEnum situacao
) {
}