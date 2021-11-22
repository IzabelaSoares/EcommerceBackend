package com.treinamento.EcommerceBackend.repositories;

import com.treinamento.EcommerceBackend.entities.AddressEntity;
import com.treinamento.EcommerceBackend.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {
}
