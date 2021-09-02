package com.leonardofadul.springboot.ionic.learning.project.dto.validations;

import com.leonardofadul.springboot.ionic.learning.project.domain.Client;
import com.leonardofadul.springboot.ionic.learning.project.domain.enums.ClientType;
import com.leonardofadul.springboot.ionic.learning.project.dto.ClientDTO;
import com.leonardofadul.springboot.ionic.learning.project.dto.validations.utils.BR;
import com.leonardofadul.springboot.ionic.learning.project.repositories.ClientRepository;
import com.leonardofadul.springboot.ionic.learning.project.resources.exceptions.FieldMessage;
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
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Client aux = clientRepository.findByEmail(objDto.getEmail());
        if(aux != null && !aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "Email already exists."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
