package br.com.base_rest_api.domain.dto.response.produto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long idProduto,
        String nome,
        BigDecimal valor
) {
}