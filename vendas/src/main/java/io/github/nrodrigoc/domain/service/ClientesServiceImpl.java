package io.github.nrodrigoc.domain.service;

import io.github.nrodrigoc.domain.entity.Cliente;
import io.github.nrodrigoc.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServiceImpl implements ClientesService{

    @Autowired
    ClientesRepository clientesRepository;

    @Override
    public List<Cliente> findAll() {
        return clientesRepository.findAll();
    }

    @Override
    public List<Cliente> findByNomeLike(String nome) {
        return clientesRepository.findByNomeLike(nome);
    }

    @Override
    public Cliente findById(Integer id) {
        return clientesRepository.findById(id).get();
    }



    @Override
    public Cliente save(Cliente cliente) {
        return clientesRepository.save(cliente);
    }

    @Override
    public void delete(Cliente cliente) {
        clientesRepository.delete(cliente);
    }

    @Override
    public void deleteById(Integer id) {
        clientesRepository.deleteById(id);
    }

    @Override
    public boolean existsByNomeIgnoreCase(String nome) {
        return clientesRepository.existsByNomeIgnoreCase(nome);
    }
}
