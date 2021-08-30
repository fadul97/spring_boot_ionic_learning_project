package com.leonardofadul.springboot.ionic.learning.project.repositories;

import com.leonardofadul.springboot.ionic.learning.project.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
