package io.github.nrodrigoc.api.dto;

import io.github.nrodrigoc.validation.NotEmptyList;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter@Setter
public class PedidoDTO {

    @NotNull(message = "Informe o código do cliente")
    private Integer cliente;

    private BigDecimal total;

    @NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
    private List<ItemPedidoDTO> itens;

}
