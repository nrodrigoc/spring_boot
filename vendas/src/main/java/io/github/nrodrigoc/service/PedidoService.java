package io.github.nrodrigoc.service;

import io.github.nrodrigoc.api.dto.PedidoDTO;
import io.github.nrodrigoc.domain.model.Pedido;

public interface PedidoService {

    Pedido salvar (PedidoDTO dto);

}
