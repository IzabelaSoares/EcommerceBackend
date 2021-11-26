package com.treinamento.EcommerceBackend.repositories;

import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Transactional(readOnly = true)
    Page<OrderEntity> findByClient(ClientEntity client, Pageable pageRequest);
}
