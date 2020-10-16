package io.github.nrodrigoc.service;

import io.github.nrodrigoc.model.Cliente;
import io.github.nrodrigoc.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {


    // Dependência do ClientesService
    private ClientesRepository repository;

    @Autowired // Injeção de dependências automática
    public ClientesService(ClientesRepository repository) {
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        repository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente) {
        //Aplica validações
    }

}
