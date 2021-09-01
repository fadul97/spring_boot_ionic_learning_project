package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.Client;
import com.leonardofadul.springboot.ionic.learning.project.dto.ClientDTO;
import com.leonardofadul.springboot.ionic.learning.project.exceptions.DataIntegrityException;
import com.leonardofadul.springboot.ionic.learning.project.exceptions.ObjectNotFoundException;
import com.leonardofadul.springboot.ionic.learning.project.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

//    public Client insert(Client obj) {
//        obj.setId(null);
//        return clientRepository.save(obj);
//    }

    public Client update(Client obj){
        Client newClient = find(obj.getId());
        updateData(newClient, obj);
        return clientRepository.save(newClient);
    }

    public void delete(Integer id){
        find(id);
        try{
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("It is not possible to delete a Client because there are related entities.");
        }
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clientRepository.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO clientDTO){
        return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
    }

    private void updateData(Client newClient, Client obj) {
        newClient.setName(obj.getName());
        newClient.setEmail(obj.getEmail());
    }
}
