package io.github.nrodrigoc.api.controller;

import io.github.nrodrigoc.api.dto.ProdutoDTO;
import io.github.nrodrigoc.domain.model.Produto;
import io.github.nrodrigoc.domain.repository.ProdutoRepository;
import io.github.nrodrigoc.exception.ProdutoNaoEncontradoException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoRepository pr;

    public ProdutoController(ProdutoRepository pr) {
        this.pr = pr;
    }

    @GetMapping
    public ProdutoDTO getById(@RequestParam Integer id){
        return pr.findById(id)
                .map(produto -> ProdutoDTO
                        .builder()
                        .nome(produto.getNome())
                        .descricao(produto.getDescricao())
                        .preco_unitario(produto.getPreco())
                        .build())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco_unitario());

        return pr.save(produto);
    }

    @GetMapping("/search")
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);

        return pr.findAll(example);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam("id") Integer id, @RequestBody @Valid ProdutoDTO dto) {
        pr.findById(id)
                .map(produtoExistente -> {
                    produtoExistente.setNome(dto.getNome());
                    produtoExistente.setDescricao(dto.getDescricao());
                    produtoExistente.setPreco(dto.getPreco_unitario());
                    return pr.save(produtoExistente);
                })
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam Integer id) {
        pr.findById(id)
                .map( produto -> {
                    pr.deleteById(id);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

}
