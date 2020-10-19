package io.github.nrodrigoc.domain.service;

import io.github.nrodrigoc.domain.entity.Cliente;

import java.util.List;

public interface ClientesService {

    List<Cliente> findAll();
    List<Cliente> findByNomeLike(String nome);
    Cliente findById(Integer id);
    Cliente save(Cliente cliente);
    void delete(Cliente cliente);
    void deleteById(Integer id);
    boolean existsByNomeIgnoreCase(String nome);

}
