package com.l2code.ContactEase.exception;

public class ContatoCadastradoException extends RuntimeException {
    public ContatoCadastradoException(){
        super("O contato com este número de celular já foi cadastrado");
    }
}
