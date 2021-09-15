package com.leonardofadul.springboot.ionic.learning.project.dto;

import com.leonardofadul.springboot.ionic.learning.project.domain.State;

import java.io.Serializable;

public class StateDTO implements Serializable {

    private Integer id;
    private String name;

    public StateDTO(){
    }

    public StateDTO(State state) {
        this.id = state.getId();
        this.name = state.getName();
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
