package br.com.base_rest_api.domain.dto.request.venda;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VendaRequest(
        @NotNull(message = "Data de emissão é obrigatória")
        LocalDateTime dataEmissao,
        @NotNull(message = "Valor total é obrigatório")
        BigDecimal valorTotal,
        @NotNull(message = "Cliente é obrigatório")
        Long idCliente,
        @NotEmpty(message = "Produtos da venda são obrigatórios")
        List<ProdutoVendaRequest> produtosVenda
) {
}