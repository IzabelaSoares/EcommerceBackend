package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.BankSlipPaymentEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Service
public class BankSlipPaymentService {

    public void generatePaymentDate(BankSlipPaymentEntity bankSlipPayment, Date instant) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        bankSlipPayment.setDueDate(calendar.getTime());
    }


}
