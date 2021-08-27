package com.leonardofadul.springboot.ionic.learning.project.repositories;

import com.leonardofadul.springboot.ionic.learning.project.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
