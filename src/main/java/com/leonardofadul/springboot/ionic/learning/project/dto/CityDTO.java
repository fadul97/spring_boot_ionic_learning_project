package com.leonardofadul.springboot.ionic.learning.project.dto;

import com.leonardofadul.springboot.ionic.learning.project.domain.City;
import com.leonardofadul.springboot.ionic.learning.project.domain.State;

public class CityDTO {

    private Integer id;
    private String name;

    public CityDTO(){
    }

    public CityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
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
}
