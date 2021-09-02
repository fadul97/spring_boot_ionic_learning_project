package com.leonardofadul.springboot.ionic.learning.project.services;

import com.leonardofadul.springboot.ionic.learning.project.domain.BankBilletPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BankBilletService {

    public void fillPaymentWithBillet(BankBilletPayment payment, Date pedidoInstant){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pedidoInstant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setDueDate(calendar.getTime());
    }
}
