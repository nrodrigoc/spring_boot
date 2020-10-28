package io.github.nrodrigoc.api.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO { // Transformada num JSON no response

    private Integer codigo;
    private String nomeCliente;
    private String cpf;
    private BigDecimal total;
    private String dataPedido;
    private String status;
    private List<InformacaoItemPedidoDTO> itens;

}
