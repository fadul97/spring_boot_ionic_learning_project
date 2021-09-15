package com.leonardofadul.springboot.ionic.learning.project.resources;

import com.leonardofadul.springboot.ionic.learning.project.domain.City;
import com.leonardofadul.springboot.ionic.learning.project.domain.Client;
import com.leonardofadul.springboot.ionic.learning.project.domain.State;
import com.leonardofadul.springboot.ionic.learning.project.dto.CityDTO;
import com.leonardofadul.springboot.ionic.learning.project.dto.ClientDTO;
import com.leonardofadul.springboot.ionic.learning.project.dto.StateDTO;
import com.leonardofadul.springboot.ionic.learning.project.services.CityService;
import com.leonardofadul.springboot.ionic.learning.project.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/states")
public class StateResource {

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StateDTO>> findAll(){
        List<State> stateList = stateService.findAll();
        List<StateDTO> stateDTOList = stateList.stream().map(StateDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(stateDTOList);
    }

    @RequestMapping(value = "/{stateId}/cities", method = RequestMethod.GET)
    public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId){
        List<City> cityList = cityService.findByState(stateId);
        List<CityDTO> cityDTOList = cityList.stream().map(CityDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(cityDTOList);
    }
}
