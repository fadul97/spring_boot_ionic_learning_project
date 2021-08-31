package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.Pedido;
import com.leonardofadul.springboot.ionic.learning.project.exceptions.ObjectNotFoundException;
import com.leonardofadul.springboot.ionic.learning.project.repositories.OrderRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    public Pedido find(Integer id){
        Optional<Pedido> obj = orderRequestRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object now found! Id: " + id + ", Type: " + Pedido.class.getName()
        ));
    }
}
