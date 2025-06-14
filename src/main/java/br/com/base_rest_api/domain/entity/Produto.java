package br.com.base_rest_api.domain.entity;

import br.com.base_rest_api.domain.enums.SituacaoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long idProduto;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "VALOR", nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEnum situacao;

    public Produto() {
        this.situacao = SituacaoEnum.ATIVO;
    }

    public Produto(Long idProduto, String nome, BigDecimal valor, SituacaoEnum situacao) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.valor = valor;
        this.situacao = situacao;
    }
}