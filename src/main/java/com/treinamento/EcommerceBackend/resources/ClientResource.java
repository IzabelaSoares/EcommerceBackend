package com.treinamento.EcommerceBackend.resources;
import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientEntity>> findAll(){
        List<ClientEntity> allClients = clientService.findAll();
        return ResponseEntity.ok().body(allClients);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientEntity> findById(@PathVariable Integer id){
        ClientEntity client = clientService.findById(id);
        return ResponseEntity.ok().body(client);
    }

}
