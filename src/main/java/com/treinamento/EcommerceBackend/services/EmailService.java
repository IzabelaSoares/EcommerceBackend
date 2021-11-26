package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.OrderEntity;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationMail(OrderEntity order);

    void sendMail(SimpleMailMessage mailMessage);
}
