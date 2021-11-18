package com.treinamento.EcommerceBackend;

import com.treinamento.EcommerceBackend.domain.Category;
import com.treinamento.EcommerceBackend.domain.Product;
import com.treinamento.EcommerceBackend.repositories.CategoryRepository;
import com.treinamento.EcommerceBackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;

@SpringBootApplication
public class EcommerceBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBackendApplication.class, args);
	}

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		Product p1 = new Product(null, "The Lord of the Rings", 0.5);
		Product p2 = new Product(null, "Smart TV", 2190.0);
		Product p3 = new Product(null, "Macbook Pro",  1250.0);
		Product p4 = new Product(null, "PC Gamer", 1200.0);
		Product p5 = new Product(null, "Rails for Dummies",100.99);
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		p1.getCategoryList().add(cat2);
		p2.getCategoryList().add(cat1);
		p2.getCategoryList().add(cat3);
		p3.getCategoryList().add(cat1);
		p3.getCategoryList().add(cat3);
		p4.getCategoryList().add(cat1);
		p4.getCategoryList().add(cat3);
		p5.getCategoryList().add(cat2);
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

	}
}
