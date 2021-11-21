package com.treinamento.EcommerceBackend.repositories;

import com.treinamento.EcommerceBackend.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<AddressEntity, Integer> {
}
