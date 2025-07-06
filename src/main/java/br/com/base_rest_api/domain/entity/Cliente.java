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

    @Column(name = "CPF", nullable = false)
    private String cpfCnpj;

    @Column(name = "NOME", nullable = false)
    private String nomeRazaoSocial;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEnum situacao;

    public Cliente() {
        this.situacao = SituacaoEnum.ATIVO;
    }

    public Cliente(Long idCliente, String cpfCnpj, String nomeRazaoSocial, SituacaoEnum situacao) {
        this.idCliente = idCliente;
        this.cpfCnpj = cpfCnpj;
        this.nomeRazaoSocial = nomeRazaoSocial;
        this.situacao = situacao;
    }
}