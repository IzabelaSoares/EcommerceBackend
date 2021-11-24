package com.treinamento.EcommerceBackend.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;

    private List<FieldMessageError> errorList = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String message) {
        super(timestamp, status, message);
    }

    public List<FieldMessageError> getErrorList() {
        return errorList;
    }

    public void addError(String fieldName, String message){
        errorList.add(new FieldMessageError(fieldName, message));
    }
}
