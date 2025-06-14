package br.com.base_rest_api.domain.dto.request.venda;

import java.time.LocalDate;

public record VendaRequestParams(
        String cliente,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}