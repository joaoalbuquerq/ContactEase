package com.l2code.ContactEase.exception;

public class ContatoNaoEncontradoException extends RuntimeException {
    public ContatoNaoEncontradoException(Long id) {
        super("Contato não cadastrado");
    }
}
