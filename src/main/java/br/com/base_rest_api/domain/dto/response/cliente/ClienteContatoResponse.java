package br.com.base_rest_api.domain.dto.response.cliente;

import br.com.base_rest_api.domain.enums.TipoContatoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteContatoResponse(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotNull(message = "Tipo é obrigatório")
        TipoContatoEnum tipoContato,
        @NotBlank(message = "Valor é obrigatório")
        String valor
) {
}