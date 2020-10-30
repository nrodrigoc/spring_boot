package io.github.nrodrigoc.api.dto;

import io.github.nrodrigoc.validation.NotEmptyList;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter@Setter
public class PedidoDTO { //Entrada de dados

    /* Formato JSON do request body
        {
            "cliente": id,
            "itens": [
                {
                    "produto": id,
                    "quantidade": quantidade
                }
            ]
        }
     */

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;

    private BigDecimal total;

    @NotEmptyList(message = "{campo.itens-pedido.obrigatorio}")
    private List<ItemPedidoDTO> itens;

}
