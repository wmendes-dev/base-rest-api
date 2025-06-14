package br.com.base_rest_api.domain.dto.request.venda;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoVendaRequest(
        Long idProdutoVenda,
        @NotNull(message = "Quantidade do produto é obrigatória")
        BigDecimal quantidade,
        @NotNull(message = "Valor unitário do produto é obrigatório")
        BigDecimal valorUnitario,
        @NotNull(message = "Valor total produto é obrigatório")
        BigDecimal valorTotal,
        @NotNull(message = "Produto é obrigatório")
        Long idProduto
) {
}