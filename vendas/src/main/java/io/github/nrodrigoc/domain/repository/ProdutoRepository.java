package io.github.nrodrigoc.domain.repository;

import io.github.nrodrigoc.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
