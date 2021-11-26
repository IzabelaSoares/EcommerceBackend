package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.entities.OrderEntity;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationMail(OrderEntity order);

    void sendMail(SimpleMailMessage mailMessage);

    void sendOrderConfirmationHtmlEmail(OrderEntity order);

    void sendHtmlEmail(MimeMessage message);

    void sendNewPasswordEmail(ClientEntity client, String newPassword);


}
