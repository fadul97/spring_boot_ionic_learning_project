package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.Category;
import com.leonardofadul.springboot.ionic.learning.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category get(Integer id){
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElse(null);
    }
}
