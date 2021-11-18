package com.treinamento.EcommerceBackend;

import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import com.treinamento.EcommerceBackend.entities.CityEntity;
import com.treinamento.EcommerceBackend.entities.ProductEntity;
import com.treinamento.EcommerceBackend.entities.StateEntity;
import com.treinamento.EcommerceBackend.repositories.CategoryRepository;
import com.treinamento.EcommerceBackend.repositories.CityRepository;
import com.treinamento.EcommerceBackend.repositories.ProductRepository;
import com.treinamento.EcommerceBackend.repositories.StateRepository;
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

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Override
	public void run(String... args) throws Exception {

		CategoryEntity cat1 = new CategoryEntity(null, "Electronics");
		CategoryEntity cat2 = new CategoryEntity(null, "Books");
		CategoryEntity cat3 = new CategoryEntity(null, "Computers");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		ProductEntity p1 = new ProductEntity(null, "The Lord of the Rings", 0.5);
		ProductEntity p2 = new ProductEntity(null, "Smart TV", 2190.0);
		ProductEntity p3 = new ProductEntity(null, "Macbook Pro",  1250.0);
		ProductEntity p4 = new ProductEntity(null, "PC Gamer", 1200.0);
		ProductEntity p5 = new ProductEntity(null, "Rails for Dummies",100.99);
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

		StateEntity state1 = new StateEntity(null, "Minas Gerais");
		StateEntity state2 = new StateEntity(null, "São Paulo");
		StateEntity state3 = new StateEntity(null, "Santa Catarina");
		stateRepository.saveAll(Arrays.asList(state1,state2,state3));

		CityEntity city1 = new CityEntity(null, "Uberlândia", state1);

		CityEntity city2 = new CityEntity(null, "Campinas", state2);
		CityEntity city3 = new CityEntity(null, "Itú", state2);

		CityEntity city4 = new CityEntity(null, "Blumenau", state3);
		CityEntity city5 = new CityEntity(null, "Florianópolis", state3);
		CityEntity city6 = new CityEntity(null, "Bombinhas", state3);
		cityRepository.saveAll(Arrays.asList(city1, city2, city3, city4, city5, city6));

	}
}
