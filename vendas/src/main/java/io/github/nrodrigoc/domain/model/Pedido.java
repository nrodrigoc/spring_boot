package io.github.nrodrigoc.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_sequence")
    private Integer id;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    //Referencia a uma chave estrangeira
    //ManyToOne quer dizer que a relação produto -> cliente é de muitos p/ um
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(scale = 2, precision = 20)
    private BigDecimal total;

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", total=" + total +
                '}';
    }
}
