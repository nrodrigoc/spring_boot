package io.github.nrodrigoc.exception;

public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException() {
        super("Senha inválida.");
    }
}
