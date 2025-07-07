package br.com.base_rest_api.domain.entity;

import br.com.base_rest_api.domain.enums.SituacaoEnum;
import br.com.base_rest_api.domain.enums.TipoPessoaEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_PESSOA", nullable = false)
    private TipoPessoaEnum tipoPessoa;

    @Column(name = "CPF_CNPJ", nullable = false)
    private String cpfCnpj;

    @Column(name = "NOME_RAZAO_SOCIAL", nullable = false)
    private String nomeRazaoSocial;

    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CIDADE")
    private String cidade;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClienteContato> contatos;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEnum situacao;

}