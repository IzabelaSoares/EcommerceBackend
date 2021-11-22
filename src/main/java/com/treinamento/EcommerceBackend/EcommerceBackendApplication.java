package com.treinamento.EcommerceBackend;

import com.treinamento.EcommerceBackend.entities.AddressEntity;
import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import com.treinamento.EcommerceBackend.entities.CityEntity;
import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.entities.ProductEntity;
import com.treinamento.EcommerceBackend.entities.StateEntity;
import com.treinamento.EcommerceBackend.entities.enums.TypeClientEnum;
import com.treinamento.EcommerceBackend.repositories.AdressRepository;
import com.treinamento.EcommerceBackend.repositories.CategoryRepository;
import com.treinamento.EcommerceBackend.repositories.CityRepository;
import com.treinamento.EcommerceBackend.repositories.ClientRepository;
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

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AdressRepository adressRepository;

	@Override
	public void run(String... args) throws Exception {

		CategoryEntity cat1 = new CategoryEntity("Electronics");
		CategoryEntity cat2 = new CategoryEntity("Books");
		CategoryEntity cat3 = new CategoryEntity("Computers");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		ProductEntity p1 = new ProductEntity("The Lord of the Rings", 0.5);
		ProductEntity p2 = new ProductEntity("Smart TV", 2190.0);
		ProductEntity p3 = new ProductEntity("Macbook Pro",  1250.0);
		ProductEntity p4 = new ProductEntity("PC Gamer", 1200.0);
		ProductEntity p5 = new ProductEntity("Rails for Dummies",100.99);
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

		StateEntity state1 = new StateEntity("Minas Gerais");
		StateEntity state2 = new StateEntity("São Paulo");
		StateEntity state3 = new StateEntity("Santa Catarina");
		stateRepository.saveAll(Arrays.asList(state1,state2,state3));

		CityEntity city1 = new CityEntity("Uberlândia", state1);
		CityEntity city2 = new CityEntity("Campinas", state2);
		CityEntity city3 = new CityEntity("Itú", state2);
		CityEntity city4 = new CityEntity("Blumenau", state3);
		CityEntity city5 = new CityEntity("Florianópolis", state3);
		CityEntity city6 = new CityEntity("Bombinhas", state3);
		cityRepository.saveAll(Arrays.asList(city1, city2, city3, city4, city5, city6));

		ClientEntity client1 = new ClientEntity("Maria", "maria@gmail.com", "103.620.968-22", TypeClientEnum.PESSOA_FISICA);
		ClientEntity client2 = new ClientEntity("Clara", "clara@gmail.com", "104.620.964-23", TypeClientEnum.PESSOA_FISICA);
		ClientEntity client3 = new ClientEntity("Maria Clara", "mariaclara@gmail.com", "107.520.968-24", TypeClientEnum.PESSOA_FISICA);


		client1.getPhonesList().add("47992880516");
		client1.getPhonesList().add("4733342637");

		client2.getPhonesList().add("47992880518");
		client2.getPhonesList().add("4733342639");

		AddressEntity address1 = new AddressEntity("Rua Palmeiras", 123, "Boa Vista", "89012201", null, city4, client1);
		AddressEntity address2 = new AddressEntity("Rua Janeiro", 124, "Bom Retiro", "89012208", null, city4, client1);
		client1.getAddressList().addAll(Arrays.asList(address1, address2));

		AddressEntity address3 = new AddressEntity("Rua Ferreiro", 13, "Anjo", "89012202", null, city5, client2);
		AddressEntity address4 = new AddressEntity("Rua Cabral", 14, "Literoi", "89012205", null, city5, client2);
		client2.getAddressList().addAll(Arrays.asList(address3, address4));

		clientRepository.saveAll(Arrays.asList(client1, client2, client3));
		adressRepository.saveAll(Arrays.asList(address1, address2, address3, address4));

	}
}
