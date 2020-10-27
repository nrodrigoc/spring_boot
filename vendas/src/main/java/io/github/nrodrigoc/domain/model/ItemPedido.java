package io.github.nrodrigoc.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "item_pedido")
@Getter@Setter
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column
    private Integer quantidade;

}
