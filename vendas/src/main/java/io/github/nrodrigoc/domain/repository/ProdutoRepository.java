package io.github.nrodrigoc.domain.repository;

import io.github.nrodrigoc.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByNomeLikeIgnoreCase(String nome);

}
