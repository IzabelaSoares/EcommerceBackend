package com.treinamento.EcommerceBackend.resources;
import com.treinamento.EcommerceBackend.domain.Category;
import com.treinamento.EcommerceBackend.domain.Product;
import com.treinamento.EcommerceBackend.services.CategoryService;
import com.treinamento.EcommerceBackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

}
