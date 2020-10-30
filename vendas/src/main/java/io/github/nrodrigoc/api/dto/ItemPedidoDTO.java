package io.github.nrodrigoc.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ItemPedidoDTO { //Entrada de dados

    /* Formato JSON do request body
        {
            "produto": id,
            "quantidade": quantidade
        }
     */

    private Integer produto;
    private Integer quantidade;

}
