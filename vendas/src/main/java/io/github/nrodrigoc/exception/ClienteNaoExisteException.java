package io.github.nrodrigoc.exception;

public class ClienteNaoExisteException extends RuntimeException{

    public ClienteNaoExisteException(Integer id) {
        super("O cliente de id " + id + " não existe.");
    }
}
