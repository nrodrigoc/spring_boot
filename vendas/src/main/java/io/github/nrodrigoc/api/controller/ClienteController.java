package io.github.nrodrigoc.api.controller;

import io.github.nrodrigoc.api.dto.ClienteDTO;
import io.github.nrodrigoc.domain.model.Cliente;
import io.github.nrodrigoc.domain.repository.ClientesRepository;
import io.github.nrodrigoc.exception.ClienteNaoExisteException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesRepository cr;

    public ClienteController(ClientesRepository cr) {
        this.cr = cr;
    }

    @GetMapping(value = {"/{id}"})
    public ClienteDTO getClienteById(@PathVariable("id") Integer id) {
        return cr.findById(id)
                .map(cliente -> converterParaDTO(cliente))//Se nao houver o cliente procurado retorna uma exception com 404
                .orElseThrow(() -> new ClienteNaoExisteException(id));

    }

    public ClienteDTO converterParaDTO(Cliente cliente) {
        return ClienteDTO
                .builder()
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .build();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Retorna um código de status personalizado
    public Cliente save(@RequestBody @Valid ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
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
    public void update(@PathVariable("id") Integer id, @RequestBody @Valid ClienteDTO dto) {

        //Se o Optional retornado pelo findById retornar um objeto, executa o que tá dentro do () do .map
        // o .map funciona como um "if" e precisa ser seguido de um .orElseGet como "else"
        cr.findById(id)
            .map(clienteExistente -> {
                clienteExistente.setNome(dto.getNome());
                clienteExistente.setCpf(dto.getCpf());
                cr.save(clienteExistente);
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
