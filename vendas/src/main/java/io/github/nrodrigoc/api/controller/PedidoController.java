package io.github.nrodrigoc.api.controller;


import io.github.nrodrigoc.api.dto.AtualizacaoStatusPedidoDTO;
import io.github.nrodrigoc.api.dto.InformacaoItemPedidoDTO;
import io.github.nrodrigoc.api.dto.InformacoesPedidoDTO;
import io.github.nrodrigoc.api.dto.PedidoDTO;
import io.github.nrodrigoc.domain.enums.StatusPedido;
import io.github.nrodrigoc.domain.model.ItemPedido;
import io.github.nrodrigoc.domain.model.Pedido;
import io.github.nrodrigoc.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController { //As operações em BD sao todas feitas através do service

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto) {
        Pedido pedidoSalvo = service.salvar(dto);
        return pedidoSalvo.getId();
    }


    @GetMapping
    public InformacoesPedidoDTO getById(@RequestParam("id") Integer id) {
        return service
                .obterPedidoCompleto(id)
                .map( p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@RequestParam("id") Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
        service.atualizarStatus(id, StatusPedido.valueOf(dto.getNovoStatus()));
    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .nomeCliente(pedido.getCliente().getNome())
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converter(pedido.getItens()))
                .build();

    }

    /**
     * Transforma cada itemPedido num objto JSON quando for mandado no response
     * @param itens
     * @return
     */
    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream()
                .map(item -> InformacaoItemPedidoDTO
                        .builder()
                        .nomeProduto(item.getProduto().getNome())
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
                ).collect(Collectors.toList());
    }


}
