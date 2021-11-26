package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

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


    protected String htmlFromTemplateOrder(OrderEntity order){
        Context context = new Context();
        context.setVariable("order", order);
        return templateEngine.process("email/order-confirmation", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(OrderEntity order){
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = prepareMimeMessageFromOrder(order);
        } catch (MessagingException e) {
            sendOrderConfirmationMail(order);
        }
        sendHtmlEmail(mimeMessage);
    }

    protected MimeMessage prepareMimeMessageFromOrder(OrderEntity order) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(order.getClient().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Order "+ order.getId() +" confirmed!");
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromTemplateOrder(order), true);
        return mimeMessage;
    }
    @Override
    public void sendNewPasswordEmail(ClientEntity client, String newPassword) {
        SimpleMailMessage simpleMailMessage = prepareNewPasswordEmail(client, newPassword);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(ClientEntity client, String newPassword){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(client.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Request For New Password!");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText("New Password: " + newPassword);
        return simpleMailMessage;
    }
}
