package br.com.base_rest_api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoContatoEnum {

    EMAIL("E-mail"),
    TELEFONE("Telefone");

    private final String descricao;

}