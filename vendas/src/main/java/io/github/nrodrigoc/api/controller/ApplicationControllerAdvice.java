package io.github.nrodrigoc.api.controller;

import io.github.nrodrigoc.api.ApiErrors;
import io.github.nrodrigoc.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegociosException(RegraNegocioException e) {
        return  new ApiErrors(e.getMessage());
    }


    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException e){
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErrors(errors);
    }

    @ExceptionHandler(ClienteNaoExisteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleClienteNaoExistException(ClienteNaoExisteException e) {
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException e) {
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException e) {
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrors handleSenhaInvalidaException(SenhaInvalidaException e) {
        return new ApiErrors(e.getMessage());
    }


}
