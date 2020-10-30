package io.github.nrodrigoc.exception;

public class UsuarioNaoEncontradoException extends RuntimeException{

    public UsuarioNaoEncontradoException(Integer id) {
        super("O usuário de id " + id + " não existe.");
    }
}
