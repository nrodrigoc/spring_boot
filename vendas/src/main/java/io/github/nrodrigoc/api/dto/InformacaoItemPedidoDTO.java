package io.github.nrodrigoc.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoItemPedidoDTO {

    /* Formato JSON para o request body

        {
            "nomeProduto": nome,
            "descricaoProduto": descricao,
            "precoUnitario": preco,
            "quantidade": quantidade
        }

     */

    private String nomeProduto;
    private String descricaoProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;

}
