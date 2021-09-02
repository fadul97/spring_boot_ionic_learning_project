package com.leonardofadul.springboot.ionic.learning.project.dto;

import com.leonardofadul.springboot.ionic.learning.project.domain.Client;
import com.leonardofadul.springboot.ionic.learning.project.dto.validations.ClientUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ClientUpdate
public class ClientDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Field 'name' required.")
    @Size(min = 5, max = 120, message = "Length must be between 5 and 120 characters.")
    private String name;

    @NotEmpty(message = "Field 'email' required.")
    @Email(message = "Invalid email.")
    private String email;

    public ClientDTO(){
    }

    public ClientDTO(Client client){
        id = client.getId();
        name = client.getName();
        email = client.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
