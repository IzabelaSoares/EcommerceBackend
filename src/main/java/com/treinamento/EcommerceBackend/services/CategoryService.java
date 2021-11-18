package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.domain.Category;
import com.treinamento.EcommerceBackend.repositories.CategoryRepository;
import com.treinamento.EcommerceBackend.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Integer id){
        Optional<Category> user = categoryRepository.findById(id);
        return user.orElseThrow(() -> new DatabaseException(id));
    }

}
