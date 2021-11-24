package com.treinamento.EcommerceBackend.services.validations;
import com.treinamento.EcommerceBackend.DTO.ClientNewDTO;
import com.treinamento.EcommerceBackend.entities.enums.TypeClientEnum;
import com.treinamento.EcommerceBackend.resources.exceptions.FieldMessageError;
import com.treinamento.EcommerceBackend.services.validations.utils.DocumentUtils;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO > {

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO clientNewDTO, ConstraintValidatorContext context) {
        List<FieldMessageError> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista
        if(clientNewDTO.getTypeClient().equals(TypeClientEnum.PESSOA_FISICA.getNumber()) && !DocumentUtils.isValidCPF(clientNewDTO.getDocumentNumber())) {
            list.add(new FieldMessageError("documentNumber","CPF Invalid!"));
        }
        if(clientNewDTO.getTypeClient().equals(TypeClientEnum.PESSOA_JURIDICA) && !DocumentUtils.isValidCNPJ(clientNewDTO.getDocumentNumber())){
            list.add(new FieldMessageError("documentNumber","CNPJ Invalid!"));
        }

        for (FieldMessageError e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
