package io.github.nrodrigoc.domain.repository;

import io.github.nrodrigoc.domain.model.Cliente;
import io.github.nrodrigoc.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);


}
