package com.leonardofadul.springboot.ionic.learning.project.repositories;

import com.leonardofadul.springboot.ionic.learning.project.domain.Client;
import com.leonardofadul.springboot.ionic.learning.project.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRequestRepository extends JpaRepository<Pedido, Integer> {

    @Transactional(readOnly = true)
    Page<Pedido> findByClient(Client client, Pageable pageRequest);
}
