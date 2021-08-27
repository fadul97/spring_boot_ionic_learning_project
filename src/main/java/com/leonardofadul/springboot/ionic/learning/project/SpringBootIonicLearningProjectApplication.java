package com.leonardofadul.springboot.ionic.learning.project;

import com.leonardofadul.springboot.ionic.learning.project.domain.Category;
import com.leonardofadul.springboot.ionic.learning.project.domain.City;
import com.leonardofadul.springboot.ionic.learning.project.domain.Product;
import com.leonardofadul.springboot.ionic.learning.project.domain.State;
import com.leonardofadul.springboot.ionic.learning.project.repositories.CategoryRepository;
import com.leonardofadul.springboot.ionic.learning.project.repositories.CityRepository;
import com.leonardofadul.springboot.ionic.learning.project.repositories.ProductRepository;
import com.leonardofadul.springboot.ionic.learning.project.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	}
}
