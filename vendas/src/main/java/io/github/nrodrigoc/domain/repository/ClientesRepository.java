package io.github.nrodrigoc.domain.repository;

import io.github.nrodrigoc.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    // Query Method do JPA onde ele reconhece o "find", "nome" como campo e "like" e o transforma no query sql
    //Método personalizado para fazer busca com Query
    //Equivalente ao SELECT_POR_NOME usado com o JdbcTemplate
    List<Cliente> findByNomeLike(String nome);

//    Query Method com @Query equivalente ao findByNomeLike(nome)
//    @Query(value = " select c from Cliente c where c.nome like :nome")
//    List <Cliente> encontrarPorNome(@Param("nome") String nome);

    boolean existsByNomeIgnoreCase(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id =:id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}
