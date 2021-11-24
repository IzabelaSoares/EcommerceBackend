package com.treinamento.EcommerceBackend.services.exceptions;


public class DataIntegrityException extends RuntimeException {

    private static final long serialVersionUID = 1;


    public DataIntegrityException(Object id){
        super("Não é possivel excluir a categoria "+ id +", pois possui produtos atrelados!");
    }


    public DataIntegrityException(String message) {
        super(message);
    }
}
