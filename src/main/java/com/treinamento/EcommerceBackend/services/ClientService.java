package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.DTO.ClientDTO;
import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.repositories.ClientRepository;
import com.treinamento.EcommerceBackend.services.exceptions.DataIntegrityException;
import com.treinamento.EcommerceBackend.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Page<ClientEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clientRepository.findAll(pageRequest);
    }

    public ClientEntity findById(Integer id){
        Optional<ClientEntity> user = clientRepository.findById(id);
        return user.orElseThrow(() -> new DatabaseException(id));
    }

    public ClientEntity insert(ClientEntity client) {
        client.setId(null);
        return clientRepository.save(client);
    }

    public void delete(Integer id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new DatabaseException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(id);
        }
    }
    
    public ClientEntity update(ClientEntity client) {
        ClientEntity clientUpdate = findById(client.getId());
        clientUpdate.setName(client.getName());
        clientUpdate.setEmail(client.getEmail());
        return clientRepository.save(clientUpdate);
    }

    public ClientEntity convertClientDTO(ClientDTO clientDTO){
        return new ClientEntity(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail());
    }

}
