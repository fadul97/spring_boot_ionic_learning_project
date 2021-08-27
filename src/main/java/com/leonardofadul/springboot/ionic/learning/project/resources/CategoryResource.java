package com.leonardofadul.springboot.ionic.learning.project.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

//    private Integer id;
//    private String abc;

    @RequestMapping(method = RequestMethod.GET)
    public String list(){
        return "Rest is working!";
    }
}
