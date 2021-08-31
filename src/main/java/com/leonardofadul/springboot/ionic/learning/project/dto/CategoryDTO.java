package com.leonardofadul.springboot.ionic.learning.project.dto;

import com.leonardofadul.springboot.ionic.learning.project.domain.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private Integer id;
    private String name;

    public CategoryDTO(){
    }

    public CategoryDTO(Category category){
        id = category.getId();
        name = category.getName();
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
