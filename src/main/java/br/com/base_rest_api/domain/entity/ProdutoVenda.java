package br.com.base_rest_api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "TB_PRODUTO_VENDA")
public class ProdutoVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO_VENDA")
    private Long idProdutoVenda;

    @Column(name = "QUANTIDADE")
    private BigDecimal quantidade;

    @Column(name = "VALOR_UNITARIO")
    private BigDecimal valorUnitario;

    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "ID_VENDA")
    private Venda venda;

}