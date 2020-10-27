package io.github.nrodrigoc.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoItemPedidoDTO {

    private String nomeProduto;
    private String descricaoProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;

}
