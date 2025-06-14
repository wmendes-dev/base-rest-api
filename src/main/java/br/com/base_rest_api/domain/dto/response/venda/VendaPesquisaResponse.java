package br.com.base_rest_api.domain.dto.response.venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VendaPesquisaResponse(
        Long idVenda,
        LocalDateTime dataEmissao,
        BigDecimal valorTotal,
        String cliente
) {
}