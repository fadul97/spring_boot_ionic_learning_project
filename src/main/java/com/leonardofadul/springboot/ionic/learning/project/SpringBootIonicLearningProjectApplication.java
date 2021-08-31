package com.leonardofadul.springboot.ionic.learning.project;

import com.leonardofadul.springboot.ionic.learning.project.domain.*;
import com.leonardofadul.springboot.ionic.learning.project.domain.enums.ClientType;
import com.leonardofadul.springboot.ionic.learning.project.domain.enums.PaymentState;
import com.leonardofadul.springboot.ionic.learning.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class SpringBootIonicLearningProjectApplication implements CommandLineRunner {

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

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIonicLearningProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informatics");
		Category cat2 = new Category(null, "Office");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProductList().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProductList().add(p2);

		p1.getCategoryList().add(cat1);
		p2.getCategoryList().addAll(Arrays.asList(cat1, cat2));
		p3.getCategoryList().add(cat1);

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCityList().add(c1);
		st2.getCityList().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.NATURALPERSON);
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
