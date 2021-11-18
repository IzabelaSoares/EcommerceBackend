package com.treinamento.EcommerceBackend.repositories;

import com.treinamento.EcommerceBackend.entities.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Integer> {
}
