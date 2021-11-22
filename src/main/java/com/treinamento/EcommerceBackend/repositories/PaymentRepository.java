package com.treinamento.EcommerceBackend.repositories;

import com.treinamento.EcommerceBackend.entities.OrderEntity;
import com.treinamento.EcommerceBackend.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
}
