package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.*;
import com.leonardofadul.springboot.ionic.learning.project.domain.enums.ClientType;
import com.leonardofadul.springboot.ionic.learning.project.domain.enums.PaymentState;
import com.leonardofadul.springboot.ionic.learning.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ItemRepository itemRepository;

    public void instantiateTestDatabase() throws ParseException {
        Category cat1 = new Category(null, "Informatics");
        Category cat2 = new Category(null, "Office");
        Category cat3 = new Category(null, "Bedroom");
        Category cat4 = new Category(null, "Electronics");
        Category cat5 = new Category(null, "Garden");
        Category cat6 = new Category(null, "Decoration");
        Category cat7 = new Category(null, "Perfumes");

        Product p1 = new Product(null, "Computer", 2000.00);
        Product p2 = new Product(null, "Printer", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);
        Product p4 = new Product(null, "Office table", 300.00);
        Product p5 = new Product(null, "Towel", 50.00);
        Product p6 = new Product(null, "Quilt", 200.00);
        Product p7 = new Product(null, "TV True Color", 1200.00);
        Product p8 = new Product(null, "Mower", 800.00);
        Product p9 = new Product(null, "Lampshade", 100.00);
        Product p10 = new Product(null, "Pendant", 180.00);
        Product p11 = new Product(null, "Shampoo", 90.00);

        cat1.getProductList().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProductList().addAll(Arrays.asList(p2, p4));
        cat3.getProductList().addAll(Arrays.asList(p5, p6));
        cat4.getProductList().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProductList().add(p8);
        cat6.getProductList().addAll(Arrays.asList(p9, p10));
        cat7.getProductList().add(p11);

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().add(cat2);
        p5.getCategories().add(cat3);
        p6.getCategories().add(cat3);
        p7.getCategories().add(cat4);
        p8.getCategories().add(cat5);
        p9.getCategories().add(cat6);
        p10.getCategories().add(cat6);
        p11.getCategories().add(cat7);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        State st1 = new State(null, "Minas Gerais");
        State st2 = new State(null, "São Paulo");

        City c1 = new City(null, "Uberlândia", st1);
        City c2 = new City(null, "São Paulo", st2);
        City c3 = new City(null, "Campinas", st2);

        st1.getCityList().add(c1);
        st2.getCityList().addAll(Arrays.asList(c2, c3));

        stateRepository.saveAll(Arrays.asList(st1, st2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

        Client client1 = new Client(null, "Maria Silva", "lsffadul@gmail.com", "36378912377", ClientType.NATURALPERSON);
        client1.getTelephoneSet().addAll(Arrays.asList("27363323", "93838393"));

        Address a1 = new Address(null, "Rua Flores", "300", "Apt 203", "Jardim", "38220834", client1, c1);
        Address a2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", client1, c2);

        client1.getAddressList().addAll(Arrays.asList(a1, a2));

        clientRepository.save(client1);
        addressRepository.saveAll(Arrays.asList(a1, a2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido order1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), client1, a1);
        Pedido order2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), client1, a2);

        Payment payment1 = new CardPayment(null, PaymentState.COMPLETED, order1, 6);
        order1.setPayment(payment1);

        Payment payment2 = new BankBilletPayment(null, PaymentState.PENDING, order2, sdf.parse("20/10/2017 00:00"), null);
        order2.setPayment(payment2);

        client1.getPedidos().addAll(Arrays.asList(order1, order2));

        orderRequestRepository.saveAll(Arrays.asList(order1, order2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));

        Item item1 = new Item(order1, p1, 0.00, 1, 2000.00);
        Item item2 = new Item(order1, p3, 0.00, 2, 80.00);
        Item item3 = new Item(order2, p2, 100.00, 1, 800.00);

        order1.getItemSet().addAll(Arrays.asList(item1, item2));
        order2.getItemSet().add(item3);

        p1.getItemSet().add(item1);
        p2.getItemSet().add(item3);
        p3.getItemSet().add(item2);

        itemRepository.saveAll(Arrays.asList(item1, item2, item3));
    }
}
