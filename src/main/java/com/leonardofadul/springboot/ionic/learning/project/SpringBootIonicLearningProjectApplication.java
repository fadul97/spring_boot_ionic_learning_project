package com.leonardofadul.springboot.ionic.learning.project;

import com.leonardofadul.springboot.ionic.learning.project.domain.Category;
import com.leonardofadul.springboot.ionic.learning.project.domain.Product;
import com.leonardofadul.springboot.ionic.learning.project.repositories.CategoryRepository;
import com.leonardofadul.springboot.ionic.learning.project.repositories.ProductRepository;
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
	}
}
