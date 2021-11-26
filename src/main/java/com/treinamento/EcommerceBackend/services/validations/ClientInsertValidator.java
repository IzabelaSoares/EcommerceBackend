package com.treinamento.EcommerceBackend.services.validations;
import com.treinamento.EcommerceBackend.dto.ClientNewDTO;
import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.entities.enums.TypeClientEnum;
import com.treinamento.EcommerceBackend.repositories.ClientRepository;
import com.treinamento.EcommerceBackend.resources.exceptions.FieldMessageError;
import com.treinamento.EcommerceBackend.services.validations.utils.DocumentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO > {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO clientNewDTO, ConstraintValidatorContext context) {
        List<FieldMessageError> list = new ArrayList<>();

        //teste cpf
        if(clientNewDTO.getTypeClient().equals(TypeClientEnum.PHYSICAL_PERSON.getNumber()) && !DocumentUtils.isValidCPF(clientNewDTO.getDocumentNumber())) {
            list.add(new FieldMessageError("documentNumber","CPF Invalid!"));
        }
        //teste cnpj
        if(clientNewDTO.getTypeClient().equals(TypeClientEnum.LEGAL_PERSON) && !DocumentUtils.isValidCNPJ(clientNewDTO.getDocumentNumber())){
            list.add(new FieldMessageError("documentNumber","CNPJ Invalid!"));
        }
        //teste email
        ClientEntity clientEmailTest = clientRepository.findByEmail(clientNewDTO.getEmail());
        if(clientEmailTest != null){
            list.add(new FieldMessageError("email", "Email informado já encontra-se cadastrado!"));
        }

        for (FieldMessageError e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
