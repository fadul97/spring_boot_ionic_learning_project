package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.Category;
import com.leonardofadul.springboot.ionic.learning.project.exceptions.DataIntegrityException;
import com.leonardofadul.springboot.ionic.learning.project.exceptions.ObjectNotFoundException;
import com.leonardofadul.springboot.ionic.learning.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category find(Integer id){
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id:" + id + ", Type: " + Category.class.getName()
        ));
    }

    public Category insert(Category obj) {
        obj.setId(null);
        return categoryRepository.save(obj);
    }

    public Category update(Category obj){
        find(obj.getId());
        return categoryRepository.save(obj);
    }

    public void delete(Integer id){
        find(id);
        try{
        categoryRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("It is not possible to delete a category that still has products.");
        }
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
