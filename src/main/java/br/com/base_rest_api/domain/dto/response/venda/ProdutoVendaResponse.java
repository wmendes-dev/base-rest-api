package br.com.base_rest_api.domain.dto.response.venda;

import br.com.base_rest_api.domain.dto.response.produto.ProdutoResponse;

import java.math.BigDecimal;

public record ProdutoVendaResponse(
        Long idProdutoVenda,
        BigDecimal quantidade,
        BigDecimal valorUnitario,
        BigDecimal valorTotal,
        ProdutoResponse produto
) {
}