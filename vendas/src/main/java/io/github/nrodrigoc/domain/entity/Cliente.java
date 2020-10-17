package io.github.nrodrigoc.domain.entity;

import javax.persistence.Id;

public class Cliente {

    private Integer id;

    private String nome;

    public Cliente() { // Construtor para setar o nome depois com setName

    }

    public Cliente(Integer id, String nome) { // Construtor para fazer consulta no BD
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) { // Construtor para criar o cliente com nome
        this.nome = nome;
    }

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
