package com.treinamento.EcommerceBackend.repositories;

import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.services.validations.ClientInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    @Transactional(readOnly = true)
    ClientEntity findByEmail(String email);
}
