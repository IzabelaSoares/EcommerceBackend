package com.treinamento.EcommerceBackend.resources;
import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import com.treinamento.EcommerceBackend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> findAll(){
        List<CategoryEntity> categories = categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryEntity> findById(@PathVariable Integer id){
        CategoryEntity category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }

}
