package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.Client;
import com.leonardofadul.springboot.ionic.learning.project.exceptions.ObjectNotFoundException;
import com.leonardofadul.springboot.ionic.learning.project.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client find(Integer id){
        Optional<Client> obj = clientRepository.findById(id);
        return  obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " +  id + ", Type: " + Client.class.getName()
        ));
    }
}
