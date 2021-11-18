package com.treinamento.EcommerceBackend.resources;
import com.treinamento.EcommerceBackend.entities.ProductEntity;
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
    public ResponseEntity<List<ProductEntity>> findAll(){
        List<ProductEntity> productList = productService.findAll();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductEntity> findById(@PathVariable Integer id){
        ProductEntity product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

}
