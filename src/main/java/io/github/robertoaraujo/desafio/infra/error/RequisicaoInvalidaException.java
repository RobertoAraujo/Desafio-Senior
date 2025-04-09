package io.github.robertoaraujo.desafio.infra.error;

public class RequisicaoInvalidaException extends RuntimeException {
    public RequisicaoInvalidaException(String message) {
        super(message);
    }
}