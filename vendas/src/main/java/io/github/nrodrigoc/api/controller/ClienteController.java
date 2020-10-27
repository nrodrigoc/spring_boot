package io.github.nrodrigoc.api.controller;

import io.github.nrodrigoc.domain.model.Cliente;
import io.github.nrodrigoc.domain.repository.ClientesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    private ClientesRepository cr;

    public ClienteController(ClientesRepository cr) {
        this.cr = cr;
    }

    @GetMapping(value = {"/{id}"})
    public Cliente getClienteById(@PathVariable("id") Integer id) {
        return cr.findById(id) //Se nao houver o cliente procurado retorna uma exception com 404
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Retorna um código de status personalizado
    public Cliente save(@RequestBody Cliente cliente) {
        return cr.save(cliente);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        cr.findById(id)
                .map(cliente -> {
                            cr.delete(cliente);
                            return Void.TYPE;
                        }
                )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }


    //Método para atualizar entidade
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {

        //Se o Optional retornado pelo findById retornar um objetio, executa o que tá dentro do () do .map
        // o .map funciona como um "if" e precisa ser seguido de um .orElseGet como "else"
        cr.findById(id)
            .map(clienteExistente -> { // o .map precisa retornar um objeto SEMPRE
                cliente.setId(clienteExistente.getId());
                cr.save(cliente);
                return Void.TYPE;
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }


    // Retorna lista de clientes
    @GetMapping
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);

        return cr.findAll(example);
    }

}
