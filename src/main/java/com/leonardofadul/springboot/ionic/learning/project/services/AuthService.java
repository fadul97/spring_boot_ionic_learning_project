package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.Client;
import com.leonardofadul.springboot.ionic.learning.project.exceptions.ObjectNotFoundException;
import com.leonardofadul.springboot.ionic.learning.project.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private Random rand = new Random();

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;

    public void sendNewPassword(String email){
        Client client = clientRepository.findByEmail(email);
        if(client == null){
            throw new ObjectNotFoundException("Email not found");
        }

        String newPass = newPassword();
        client.setPassword(pe.encode(newPass));

        clientRepository.save(client);
        emailService.sendNewPasswordEmail(client, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for(int i = 0; i < 10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if(opt == 0){   // generate a digit
            return (char)(rand.nextInt(10) + 48);
        } else if(opt == 1){ // generate an uppercase letter
            return (char)(rand.nextInt(26) + 65);
        } else{     // generate a lowercase letter
            return (char)(rand.nextInt(26) + 97);
        }
    }
}
