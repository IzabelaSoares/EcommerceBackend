package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.ProductEntity;
import com.treinamento.EcommerceBackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> findAll(){
        return productRepository.findAll();
    }

    public ProductEntity findById(Integer id){
        Optional<ProductEntity> product = productRepository.findById(id);
        return product.get();
    }

}
