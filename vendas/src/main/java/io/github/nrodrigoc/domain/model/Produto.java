package io.github.nrodrigoc.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_sequence")
    private Integer id;

    @Column(length = 30, nullable = false)
    private String nome;

    @Column(length = 100)
    private String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal preco;

}
