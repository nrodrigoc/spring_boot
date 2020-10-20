package io.github.nrodrigoc;

import io.github.nrodrigoc.domain.entity.Cliente;
//import io.github.nrodrigoc.domain.repository.ClientesRepository;
import io.github.nrodrigoc.domain.entity.Pedido;
import io.github.nrodrigoc.domain.repository.ClientesRepository;

import io.github.nrodrigoc.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired ClientesRepository cr,
            @Autowired PedidoRepository pr
            ) { // @Autowired no parametro para referenciar o repository
        return args -> {

//            Cliente mauro = new Cliente("Paulo");
//            cr.save(mauro);
//
//            Pedido p = new Pedido();
//            p.setCliente(mauro);
//            p.setDataPedido(LocalDate.now()); // Data atual pelo Java 8
//            p.setTotal(BigDecimal.valueOf(100));
//            pr.save(p);
//
//            Cliente cliente = cr.findClienteFetchPedidos(mauro.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            System.out.println("Pedidos do cliente chamado Mauro: \n");
            List<Pedido> mauroPedidos = pr.findByCliente(cr.findById(1).get());
            mauroPedidos.forEach(System.out::println);

        };
    }



    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
