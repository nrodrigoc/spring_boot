package io.github.nrodrigoc;

import io.github.nrodrigoc.domain.entity.Cliente;
//import io.github.nrodrigoc.domain.repository.ClientesRepository;
import io.github.nrodrigoc.domain.service.ClientesServiceImpl;
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
    public CommandLineRunner init(@Autowired ClientesServiceImpl cls) { // @Autowired no parametro para referenciar o repository
        return args -> {

            cls.save(new Cliente("Mauro"));

            cls.save(new Cliente("Patricia"));

            cls.save(new Cliente("maria"));

            System.out.println("Lista de todos no BD");
            List<Cliente> todosClientes = cls.findAll();
            todosClientes.forEach(System.out::println);

            // Verificando se existe um cliente chamado "Patricia"
            System.out.println("\nA entidade Patricia existe? R:" + cls.existsByNomeIgnoreCase("patricia"));

//            cls.deleteById(1);
//            System.out.println("\nClientes apos deletar o id 1");
//            todosClientes = cls.findAll();
//            todosClientes.forEach(System.out::println);

            System.out.println("\nRetorno de busca por nome utilizando a letra 'a':");
            cls.findByNomeLike("a").forEach(System.out::println);

            System.out.println("\nApós adicionar um novo cliente");
            cls.save(new Cliente("Celestino"));
            todosClientes = cls.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("\nAtualizando uma cliente chamada \"maria\" por \"Maria Atualizada\":");
            cls.findByNomeLike("maria").forEach(c -> {
                c.setNome("Maria Atualizada");
                cls.save(c);
            });
            todosClientes = cls.findAll();
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
