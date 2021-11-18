package com.treinamento.EcommerceBackend.services.exceptions;


import javax.persistence.criteria.CriteriaBuilder;

public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = 1;


    public DatabaseException(Object id){
        super("Id " + id + " not found!");
    }


    public DatabaseException(String message) {
        super(message);
    }
}
