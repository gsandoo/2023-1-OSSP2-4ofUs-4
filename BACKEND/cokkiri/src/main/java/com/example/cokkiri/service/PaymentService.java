package com.example.cokkiri.service;

import com.example.cokkiri.model.Payment;
import com.example.cokkiri.model.User;
import com.example.cokkiri.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    //결제 내역 저장
    public Payment save(Payment payment){
        paymentRepository.save(payment);
        return payment;
    }
}
