package br.com.base_rest_api.domain.dto.request.produto;

import br.com.base_rest_api.domain.enums.SituacaoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotNull(message = "Valor é obrigatório")
        BigDecimal valor,
        @NotNull(message = "Situação é obrigatória")
        SituacaoEnum situacao
) {
}