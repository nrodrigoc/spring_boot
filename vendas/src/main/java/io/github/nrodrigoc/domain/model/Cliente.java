package io.github.nrodrigoc.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity //Utilizando o JPA para fazer injeçao no BD
@Getter@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_sequence") // Gerar automaticamente os id's dos clientes
    private Integer id;

    //Relacao do cliente com seus pedidos, onde um cliente pode ter muitos pedidos
    //Usamos Set e nao Collections ou List pois um pedido nao pode se repetir
    //O parâmetro mappedBy deve conter o nome do campo que está dentro da classe Pedido
    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;

//    @NotBlank //Impede a criacao de uma entidade com esse campo null
    @Column(length = 100) // Usado para referenciar a coluna na tabela
    @NotEmpty(message = "Campo nome é obrigatório")
    private String nome;

    @Column(length = 11)
    @NotEmpty(message = "Campo CPF é obrigatório")
    @CPF(message = "Informe um CPF válido")
    private String cpf;

    public Cliente() { // Construtor para setar o nome depois com setName

    }

    public Cliente(Integer id, String nome) { // Construtor para fazer consulta no BD
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) { // Construtor para criar o cliente com nome
        this.nome = nome;
    } // Construtor para criar entidade já com nome

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
