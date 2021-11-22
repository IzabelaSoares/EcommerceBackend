package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.repositories.ClientRepository;
import com.treinamento.EcommerceBackend.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientEntity> findAll(){
        return clientRepository.findAll();
    }

    public ClientEntity findById(Integer id){
        Optional<ClientEntity> user = clientRepository.findById(id);
        return user.orElseThrow(() -> new DatabaseException(id));
    }

}
