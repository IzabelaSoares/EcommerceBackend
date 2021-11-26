package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationMail(OrderEntity order) {
        SimpleMailMessage message = prepareSimpleMailMessage(order);
        sendMail(message);
    }

    protected SimpleMailMessage prepareSimpleMailMessage(OrderEntity order){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(order.getClient().getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Order "+ order.getId() +" Confirmed!");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(order.toString());
        return simpleMailMessage;
    }


}
