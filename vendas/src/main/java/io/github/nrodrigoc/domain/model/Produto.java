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
    @NotEmpty(message = "Campo nome é obrigatório")
    private String nome;

    @Column(length = 100)
    @NotEmpty(message = "Campo descrição é obrigatório")
    private String descricao;

    @Column(name = "preco_unitario", nullable = false)
    @NotNull(message = "Campo preço é obrigatório")
    private BigDecimal preco;

}
