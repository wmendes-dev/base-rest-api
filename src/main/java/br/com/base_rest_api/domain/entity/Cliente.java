package br.com.base_rest_api.domain.entity;

import br.com.base_rest_api.domain.enums.SituacaoEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEnum situacao;

    public Cliente() {
        this.situacao = SituacaoEnum.ATIVO;
    }

    public Cliente(Long idCliente, String nome, String cpf, SituacaoEnum situacao) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.situacao = situacao;
    }
}