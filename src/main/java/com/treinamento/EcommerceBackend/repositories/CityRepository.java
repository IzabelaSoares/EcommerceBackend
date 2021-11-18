package com.treinamento.EcommerceBackend.repositories;

import com.treinamento.EcommerceBackend.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
}
