package com.leonardofadul.springboot.ionic.learning.project.repositories;

import com.leonardofadul.springboot.ionic.learning.project.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
