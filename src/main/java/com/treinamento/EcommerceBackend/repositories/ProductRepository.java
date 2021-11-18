package com.treinamento.EcommerceBackend.repositories;

import com.treinamento.EcommerceBackend.domain.Category;
import com.treinamento.EcommerceBackend.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
