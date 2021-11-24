package com.treinamento.EcommerceBackend.services.exceptions;


public class DataIntegrityException extends RuntimeException {

    private static final long serialVersionUID = 1;


    public DataIntegrityException(Object id){
        super("Não é possivel excluir o objeto de Id "+ id +", pois possui dados atrelados!");
    }

    public DataIntegrityException(String message) {
        super(message);
    }
}
