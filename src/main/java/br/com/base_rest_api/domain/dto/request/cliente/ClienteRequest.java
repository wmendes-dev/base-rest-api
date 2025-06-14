package br.com.base_rest_api.domain.dto.request.cliente;

import br.com.base_rest_api.domain.enums.SituacaoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "CPF é obrigatório")
        String cpf,
        @NotNull(message = "Situação é obrigatória")
        SituacaoEnum situacao
) {
}