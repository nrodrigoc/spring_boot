package io.github.nrodrigoc.service.impl;

import io.github.nrodrigoc.api.dto.ItemPedidoDTO;
import io.github.nrodrigoc.api.dto.PedidoDTO;
import io.github.nrodrigoc.domain.model.Cliente;
import io.github.nrodrigoc.domain.model.ItemPedido;
import io.github.nrodrigoc.domain.model.Pedido;
import io.github.nrodrigoc.domain.model.Produto;
import io.github.nrodrigoc.domain.repository.ClientesRepository;
import io.github.nrodrigoc.domain.repository.ItemPedidoRepository;
import io.github.nrodrigoc.domain.repository.PedidosRepository;
import io.github.nrodrigoc.domain.repository.ProdutoRepository;
import io.github.nrodrigoc.exception.RegraNegocioException;
import io.github.nrodrigoc.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Cria um construto com todos os campos "final"
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional // Usado quando há muitas alterações no banco pelo método para garantir segurança
    public Pedido salvar(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        Cliente cliente = clientesRepository.
                findById(dto.getCliente())
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedidos = converterItens(pedido, dto.getItens());
        pedidosRepository.save(pedido);

        itemPedidoRepository.saveAll(itensPedidos);

        pedido.setItens(itensPedidos);
        return pedido;

    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> itens) {
        if(itens.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens");
        }

        return itens
                .stream() //Stream de dto's
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository.findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));


                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList()); //

    }

}
