package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.State;
import com.leonardofadul.springboot.ionic.learning.project.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<State> findAll(){
        return stateRepository.findAllByOrderByName();
    }
}
