package com.leonardofadul.springboot.ionic.learning.project.resources;

import com.leonardofadul.springboot.ionic.learning.project.domain.Category;
import com.leonardofadul.springboot.ionic.learning.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        Category obj = categoryService.get(id);
        return ResponseEntity.ok().body(obj);
    }
}
