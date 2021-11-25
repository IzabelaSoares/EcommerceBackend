package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import com.treinamento.EcommerceBackend.entities.ProductEntity;
import com.treinamento.EcommerceBackend.repositories.CategoryRepository;
import com.treinamento.EcommerceBackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductEntity> findAll(){
        return productRepository.findAll();
    }

    public ProductEntity findById(Integer id){
        Optional<ProductEntity> product = productRepository.findById(id);
        return product.get();
    }

    public Page<ProductEntity> search(String name, List<Integer> listId, Integer page,
                                              Integer linesPerPage, String orderBy, String direction){

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<CategoryEntity> categoryList = categoryRepository.findAllById(listId);
        return productRepository.findDistinctByNameContainingAndCategoryListIn(name, categoryList, pageRequest);

    }

}
