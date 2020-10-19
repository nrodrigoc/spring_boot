package io.github.nrodrigoc.domain.repository;

import io.github.nrodrigoc.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    // Query Method do JPA onde ele reconhece o "find", "nome" como campo e "like" e o transforma no query sql
    //Método personalizado para fazer busca com Query
    //Equivalente ao SELECT_POR_NOME usado com o JdbcTemplate
    List<Cliente> findByNomeLike(String nome);
    boolean existsByNomeIgnoreCase(String nome);

    //    private static String SELECT_POR_NOME = "select * from cliente where nome like ?"
}
