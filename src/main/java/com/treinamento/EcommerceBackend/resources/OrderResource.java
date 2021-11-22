package com.treinamento.EcommerceBackend.resources;
import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import com.treinamento.EcommerceBackend.entities.OrderEntity;
import com.treinamento.EcommerceBackend.services.CategoryService;
import com.treinamento.EcommerceBackend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderEntity>> findAll(){
        List<OrderEntity> orderList = orderService.findAll();
        return ResponseEntity.ok().body(orderList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderEntity> findById(@PathVariable Integer id){
        OrderEntity order = orderService.findById(id);
        return ResponseEntity.ok().body(order);
    }

}
