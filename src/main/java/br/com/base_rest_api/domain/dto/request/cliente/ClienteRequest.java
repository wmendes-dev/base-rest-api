package br.com.base_rest_api.domain.dto.request.cliente;

import br.com.base_rest_api.domain.enums.SituacaoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequest(
        @NotBlank(message = "CPF é obrigatório")
        String cpfCnpj,
        @NotBlank(message = "Nome é obrigatório")
        String nomeRazaoSocial,
        @NotNull(message = "Situação é obrigatória")
        SituacaoEnum situacao
) {
}