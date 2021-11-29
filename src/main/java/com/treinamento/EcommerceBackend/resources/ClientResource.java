package com.treinamento.EcommerceBackend.resources;

import com.treinamento.EcommerceBackend.dto.ClientDTO;
import com.treinamento.EcommerceBackend.dto.ClientNewDTO;
import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<ClientEntity> clients = clientService.findAll();
        List<ClientDTO> clientList = clients.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clientList);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientEntity> findById(@PathVariable Integer id){
        ClientEntity client = clientService.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClientDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<ClientEntity> clients = clientService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClientDTO> clientsDTOList = clients.map(client -> new ClientDTO(client));
        return ResponseEntity.ok().body(clientsDTOList);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO clientNewDTO){
        ClientEntity client = clientService.convertClientDTO(clientNewDTO);
        client = clientService.insert(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO clientDTO){
        ClientEntity client = clientService.convertClientDTO(clientDTO);
        client.setId(id);
        client = clientService.update(client);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value="/picture")
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
        URI uri = clientService.uploadProfilePicture(file);
        return ResponseEntity.created(uri).build();
    }

}
