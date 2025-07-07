package br.com.base_rest_api.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoPessoaEnum {

    PF("Pessoa Física"),
    PJ("Pessoa Jurídica");

    private final String descricao;

}