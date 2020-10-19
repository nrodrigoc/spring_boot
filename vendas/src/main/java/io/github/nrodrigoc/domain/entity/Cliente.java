package io.github.nrodrigoc.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity //Utilizando o JPA para fazer injeçao no BD
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Gerar automaticamente os id's dos clientes
    private Integer id;

//    @NotBlank //Impede a criacao de uma entidade com esse campo null
    @Column(length = 100, nullable = false) // Usado para referenciar a coluna na tabela
    private String nome;

    public Cliente() { // Construtor para setar o nome depois com setName

    }

    public Cliente(Integer id, String nome) { // Construtor para fazer consulta no BD
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) { // Construtor para criar o cliente com nome
        this.nome = nome;
    } // Construtor para criar entidade já com nome

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
