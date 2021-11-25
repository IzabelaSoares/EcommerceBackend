package com.treinamento.EcommerceBackend.services.validations;
import com.treinamento.EcommerceBackend.DTO.ClientDTO;
import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.repositories.ClientRepository;
import com.treinamento.EcommerceBackend.resources.exceptions.FieldMessageError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void initialize(ClientUpdate ann) {
    }

    @Override
    public boolean isValid(ClientDTO clientDTO, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> mapList = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        Integer uriId = Integer.parseInt(mapList.get("id"));

        List<FieldMessageError> list = new ArrayList<>();

        //teste email
        ClientEntity clientEmailTest = clientRepository.findByEmail(clientDTO.getEmail());
        if(clientEmailTest != null && !clientEmailTest.getId().equals(uriId)){
            list.add(new FieldMessageError("email", "Email informado já encontra-se cadastrado!"));
        }
        for (FieldMessageError e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
