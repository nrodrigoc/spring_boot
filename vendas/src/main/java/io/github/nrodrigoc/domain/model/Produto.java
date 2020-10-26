package io.github.nrodrigoc.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_sequence")
    private Integer id;

    @Column(length = 30, nullable = false)
    private String nome;

    @Column(length = 100)
    private String descricao;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal preco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
