package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import com.treinamento.EcommerceBackend.entities.OrderEntity;
import com.treinamento.EcommerceBackend.repositories.CategoryRepository;
import com.treinamento.EcommerceBackend.repositories.OrderRepository;
import com.treinamento.EcommerceBackend.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> findAll(){
        return orderRepository.findAll();
    }

    public OrderEntity findById(Integer id){
        Optional<OrderEntity> user = orderRepository.findById(id);
        return user.orElseThrow(() -> new DatabaseException(id));
    }

}
