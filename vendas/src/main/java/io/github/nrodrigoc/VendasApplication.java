package io.github.nrodrigoc;

import io.github.nrodrigoc.domain.entity.Cliente;
import io.github.nrodrigoc.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) { // @Autowired no parametro para referenciar o repository
        return args -> {

//            clientes.salvar(new Cliente("Mauro"));

//            clientes.salvar(new Cliente("Patricia"));

            System.out.println("Lista de todos no BD");
            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            clientes.deletar(1);
            System.out.println("\nClientes apos deletar o id 1");
            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("\nRetorno de busca por nome utilizando a letra 'a':");
            clientes.buscaPorNome("a").forEach(System.out::println);

            System.out.println("\nApós adicionar um novo cliente");
            clientes.salvar(new Cliente("Celestino"));
            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("\nAtualizando uma cliente chamada \"maria\" por \"Maria Atualizada\":");
            clientes.buscaPorNome("maria").forEach(c -> {
                c.setNome("Maria Atualizada");
                clientes.atualizar(c);
            });
            todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);


//            Deletar todos os clientes
//            clientes.obterTodos().forEach(c -> {
//                clientes.deletar(c);
//            });
        };
    }



    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
