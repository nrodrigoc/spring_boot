package io.github.nrodrigoc.api.controller;


import io.github.nrodrigoc.api.dto.PedidoDTO;
import io.github.nrodrigoc.domain.model.Pedido;
import io.github.nrodrigoc.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedidoSalvo = service.salvar(dto);
        return pedidoSalvo.getId();
    }

}
