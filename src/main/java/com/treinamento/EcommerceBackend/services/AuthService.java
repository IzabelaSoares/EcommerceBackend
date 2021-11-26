package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.repositories.ClientRepository;
import com.treinamento.EcommerceBackend.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email) {
        ClientEntity client = clientRepository.findByEmail(email);
        if (client == null) {
            throw new DatabaseException("Email not found!");
        }
        String newPassword = newPassword();
        client.setPassword(encoder.encode(newPassword));
        clientRepository.save(client);
        emailService.sendNewPasswordEmail(client, newPassword);
    }

    private String newPassword() {
        char[] array = new char[10];
        for (int i = 0; i < 10; i++) {
            array[i] = randomChar();
        }
        return array.toString();
    }

    private char randomChar() {
        int option = random.nextInt(3);
        if (option == 0) { //random a number 0-9
            return (char) (random.nextInt(10) + 48);
        } else if (option == 0) { //random a LETTER
            return (char) (random.nextInt(26) + 65);
        } else //random a letter
            return (char) (random.nextInt(26) + 97);
    }
}
