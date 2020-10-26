package io.github.nrodrigoc.domain.api.controller;

import io.github.nrodrigoc.domain.model.Cliente;
import io.github.nrodrigoc.domain.repository.ClientesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {


    private ClientesRepository cr;

    public ClienteController(ClientesRepository cr) {
        this.cr = cr;
    }

    @GetMapping(value = {"/{id}"})
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = cr.findById(id);

        if (cliente.isPresent()) {
//            ResponseEntity<Cliente> responseEntity = new ResponseEntity<>(cliente.get(), HttpStatus.OK);
//            return responseEntity;
            return ResponseEntity.ok(cliente.get()); // Mesma coisa que o retorno acima
            //Retorna um JSON com as informações do cliente - Código 200
        }

        return ResponseEntity.notFound().build(); // Retorna o código 404

    }

//    @RequestMapping(value = {"/hello/{nome}"}, method = RequestMethod.GET)
//    @GetMapping(value = {"/hello/{nome}"})
//    @ResponseBody // O metodo precisa retornar uma String
//    public String helloCliente(@PathVariable("nome") String nome) { //Recebe o nome pelo path
//        return  String.format("Hello %s", nome);
//    }

    @PostMapping
    @ResponseBody
    public ResponseEntity save( @RequestBody Cliente cliente) {
        Cliente clienteSalvo = cr.save(cliente);

        return ResponseEntity.ok(clienteSalvo);
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete ( @PathVariable Integer id) {
        Optional<Cliente> cliente = cr.findById(id);

        if(cliente.isPresent()) {
            cr.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }


        return ResponseEntity.notFound().build();
    }


    //Método para atualizar entidade
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {

        //Se o Optional retornado pelo findById retornar um objetio, executa o que tá dentro do () do .map
        // o .map funciona como um "if" e precisa ser seguido de um .orElseGet como "else"
        return cr
                .findById(id)
                .map( clienteExistente -> { // o .map precisa retornar um objeto SEMPRE
                    cliente.setId(clienteExistente.getId());
                    cr.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build()); // Else resulta 404

    }


    // Retorna lista de clientes
    @GetMapping
    @ResponseBody
    public ResponseEntity find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        List<Cliente> clientes = cr.findAll(example);

        return ResponseEntity.ok(clientes);
    }

}
