package com.br.tc.core.ports.service;

import com.br.tc.core.model.Order;

public interface PaymentPort {
    String gerarQrCode(Order order);
}
