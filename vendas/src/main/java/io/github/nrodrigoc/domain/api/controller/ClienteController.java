package io.github.nrodrigoc.domain.api.controller;

import io.github.nrodrigoc.domain.model.Cliente;
import io.github.nrodrigoc.domain.repository.ClientesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
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

}
