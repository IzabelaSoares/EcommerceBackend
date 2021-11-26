package com.treinamento.EcommerceBackend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService{

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendMail(SimpleMailMessage mailMessage) {
        LOG.info("Sending a mail");
        LOG.info(mailMessage.toString());
        LOG.info("Email sent!");
    }

    @Override
    public void sendHtmlEmail(MimeMessage htmlMessage) {
        LOG.info("Sending a mail");
        LOG.info(htmlMessage.toString());
        LOG.info("Email sent!");
    }
}
