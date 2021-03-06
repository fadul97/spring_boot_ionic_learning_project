package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.Address;
import com.leonardofadul.springboot.ionic.learning.project.domain.City;
import com.leonardofadul.springboot.ionic.learning.project.domain.Client;
import com.leonardofadul.springboot.ionic.learning.project.domain.enums.ClientType;
import com.leonardofadul.springboot.ionic.learning.project.domain.enums.Profile;
import com.leonardofadul.springboot.ionic.learning.project.dto.ClientDTO;
import com.leonardofadul.springboot.ionic.learning.project.dto.ClientNewDTO;
import com.leonardofadul.springboot.ionic.learning.project.exceptions.AuthorizationException;
import com.leonardofadul.springboot.ionic.learning.project.exceptions.DataIntegrityException;
import com.leonardofadul.springboot.ionic.learning.project.exceptions.ObjectNotFoundException;
import com.leonardofadul.springboot.ionic.learning.project.repositories.AddressRepository;
import com.leonardofadul.springboot.ionic.learning.project.repositories.CityRepository;
import com.leonardofadul.springboot.ionic.learning.project.repositories.ClientRepository;
import com.leonardofadul.springboot.ionic.learning.project.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer size;

    public Client find(Integer id){
        UserSS user = UserService.authenticated();
        if(user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Access denied");
        }

        Optional<Client> obj = clientRepository.findById(id);
        return  obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " +  id + ", Type: " + Client.class.getName()
        ));
    }

    @Transactional
    public Client insert(Client obj) {
        obj.setId(null);
        obj = clientRepository.save(obj);
        addressRepository.saveAll(obj.getAddressList());
        return obj;
    }

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
            throw new DataIntegrityException("It is not possible to delete a Client because there are related pedidos.");
        }
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findByEmail(String email){
        UserSS user = UserService.authenticated();
        if(user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())){
            throw new AuthorizationException("Access denied");
        }

        Client obj = clientRepository.findByEmail(email);
        if(obj == null){
            throw new ObjectNotFoundException("Object not found! Id: " + user.getId() + ", Type: " + Client.class.getName());
        }

        return obj;
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clientRepository.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO clientDTO){
        return new Client(clientDTO.getId(), clientDTO.getName(), null, clientDTO.getEmail(), null, null);
    }

    public Client fromDTO(ClientNewDTO clientNewDTO){
        Client client = new Client(null, clientNewDTO.getName(), clientNewDTO.getEmail(), pe.encode(clientNewDTO.getPassword()), clientNewDTO.getCpfOrCnpj(), ClientType.toEnum(clientNewDTO.getClientType()));

        City city = new City(clientNewDTO.getCityId(), null, null);

        Address address = new Address(null, clientNewDTO.getStreet(), clientNewDTO.getNumber(), clientNewDTO.getComplement(), clientNewDTO.getDistrict(), clientNewDTO.getZipCode(), client, city);

        client.getAddressList().add(address);
        client.getTelephoneSet().add(clientNewDTO.getTelephone1());
        if(clientNewDTO.getTelephone2() != null){
            client.getTelephoneSet().add(clientNewDTO.getTelephone2());
        }
        if(clientNewDTO.getTelephone3() != null){
            client.getTelephoneSet().add(clientNewDTO.getTelephone3());
        }

        return  client;
    }

    private void updateData(Client newClient, Client obj) {
        newClient.setName(obj.getName());
        newClient.setEmail(obj.getEmail());
    }

    public URI uploadProfilePicture(MultipartFile multipartFile){
        UserSS user = UserService.authenticated();
        if(user == null){
            throw new AuthorizationException("Access denied.");
        }

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);
        String fileName = prefix + user.getId() + ".jpg";

        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }
}
