package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.domain.Category;
import com.treinamento.EcommerceBackend.domain.Product;
import com.treinamento.EcommerceBackend.repositories.CategoryRepository;
import com.treinamento.EcommerceBackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Integer id){
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

}
