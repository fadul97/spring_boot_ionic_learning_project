package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.BankBilletPayment;
import com.leonardofadul.springboot.ionic.learning.project.domain.Item;
import com.leonardofadul.springboot.ionic.learning.project.domain.Pedido;
import com.leonardofadul.springboot.ionic.learning.project.domain.enums.PaymentState;
import com.leonardofadul.springboot.ionic.learning.project.exceptions.ObjectNotFoundException;
import com.leonardofadul.springboot.ionic.learning.project.repositories.ItemRepository;
import com.leonardofadul.springboot.ionic.learning.project.repositories.OrderRequestRepository;
import com.leonardofadul.springboot.ionic.learning.project.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    @Autowired
    private BankBilletService bankBilletService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ClientService clientService;

    public Pedido find(Integer id){
        Optional<Pedido> obj = orderRequestRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object now found! Id: " + id + ", Type: " + Pedido.class.getName()
        ));
    }

    @Transactional
    public Pedido insert(Pedido obj){
        obj.setId(null);
        obj.setInstant(new Date());
        obj.setClient(clientService.find(obj.getClient().getId()));
        obj.getPayment().setPaymentState(PaymentState.PENDING);
        obj.getPayment().setPedido(obj);

        if(obj.getPayment() instanceof BankBilletPayment){
            BankBilletPayment payment = (BankBilletPayment) obj.getPayment();
            bankBilletService.fillPaymentWithBillet(payment, obj.getInstant());
        }

        obj = orderRequestRepository.save(obj);
        paymentRepository.save(obj.getPayment());
        for(Item item : obj.getItemSet()){
            item.setDiscount(0.0);
            item.setProduct(productService.find(item.getProduct().getId()));
            item.setPrice(item.getProduct().getPrice());
            item.setPedido(obj);
        }

        itemRepository.saveAll(obj.getItemSet());
        System.out.println(obj);
        return obj;
    }
}
