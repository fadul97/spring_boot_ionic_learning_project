package com.leonardofadul.springboot.ionic.learning.project.resources;

import com.leonardofadul.springboot.ionic.learning.project.domain.Client;
import com.leonardofadul.springboot.ionic.learning.project.dto.ClientDTO;
import com.leonardofadul.springboot.ionic.learning.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> find(@PathVariable Integer id){
        Client obj = clientService.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDTO, @PathVariable Integer id){
        Client obj = clientService.fromDTO(objDTO);
        obj.setId(id);
        obj = clientService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<Client> ClientList = clientService.findAll();
        List<ClientDTO> ClientDTOList = ClientList.stream().map(ClientDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(ClientDTOList);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClientDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                      @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                      @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<Client> ClientPage = clientService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClientDTO> ClientDTOPage = ClientPage.map(ClientDTO::new);
        return ResponseEntity.ok().body(ClientDTOPage);
    }
}
