package br.com.base_rest_api.domain.entity;

import br.com.base_rest_api.domain.enums.TipoContatoEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_CLIENTE_CONTATO")
public class ClienteContato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE_CONTATO")
    private Long idClienteContato;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CONTATO", nullable = false)
    private TipoContatoEnum tipoContato;

    @Column(name = "VALOR", nullable = false)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

}