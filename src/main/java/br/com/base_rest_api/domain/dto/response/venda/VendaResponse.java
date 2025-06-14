package br.com.base_rest_api.domain.dto.response.venda;

import br.com.base_rest_api.domain.dto.response.cliente.ClienteResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VendaResponse(
        Long idVenda,
        LocalDateTime dataEmissao,
        BigDecimal valorTotal,
        ClienteResponse cliente,
        List<ProdutoVendaResponse> produtosVenda
) {
}