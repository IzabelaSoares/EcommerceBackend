package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.dto.ClientDTO;
import com.treinamento.EcommerceBackend.dto.ClientNewDTO;
import com.treinamento.EcommerceBackend.entities.AddressEntity;
import com.treinamento.EcommerceBackend.entities.CityEntity;
import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.entities.enums.TypeClientEnum;
import com.treinamento.EcommerceBackend.repositories.AdressRepository;
import com.treinamento.EcommerceBackend.repositories.ClientRepository;
import com.treinamento.EcommerceBackend.services.exceptions.DataIntegrityException;
import com.treinamento.EcommerceBackend.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdressRepository adressRepository;

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

    @Transactional
    public ClientEntity insert(ClientEntity client) {
        client.setId(null);
        client = clientRepository.save(client);
        adressRepository.saveAll(client.getAddressList());
        return client;
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
        ClientEntity client = new ClientEntity(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null);
        return client;
    }

    public ClientEntity convertClientDTO(ClientNewDTO clientNewDTO){
        ClientEntity client = new ClientEntity(clientNewDTO.getName(), clientNewDTO.getEmail(),
                clientNewDTO.getDocumentNumber(), TypeClientEnum.toEnum(clientNewDTO.getTypeClient()), passwordEncoder.encode(clientNewDTO.getPassword()));
        CityEntity city = new CityEntity(clientNewDTO.getCityId(), null,null);
        AddressEntity address = new AddressEntity(clientNewDTO.getStreet(), clientNewDTO.getNumber(), clientNewDTO.getDistrict(),
                 clientNewDTO.getCode(), clientNewDTO.getComplement(),city, client);
        client.getAddressList().add(address);
        client.getPhonesList().add(clientNewDTO.getPhone1());
        if(clientNewDTO.getPhone2() != null){
            client.getPhonesList().add(clientNewDTO.getPhone2());
        }
        if(clientNewDTO.getPhone3() != null){
            client.getPhonesList().add(clientNewDTO.getPhone3());
        }
        return client;
    }

}
