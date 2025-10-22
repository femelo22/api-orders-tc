package com.br.tc.core.ports.service;

import com.br.tc.core.model.Order;
import com.br.tc.core.model.Payment;

public interface PaymentPort {
    String gerarQrCode(Order order);
    Payment updatePayment(Payment payment);
}
