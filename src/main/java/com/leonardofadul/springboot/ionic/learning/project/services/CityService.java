package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.City;
import com.leonardofadul.springboot.ionic.learning.project.domain.State;
import com.leonardofadul.springboot.ionic.learning.project.repositories.CityRepository;
import com.leonardofadul.springboot.ionic.learning.project.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> findByState(Integer stateId){
        return cityRepository.findCities(stateId);
    }
}
