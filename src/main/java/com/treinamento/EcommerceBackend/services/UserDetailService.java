package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.repositories.ClientRepository;
import com.treinamento.EcommerceBackend.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ClientEntity client = clientRepository.findByEmail(email);
        if(client == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(client.getId(), client.getEmail(), client.getPassword(), client.getProfileList());
    }
}
