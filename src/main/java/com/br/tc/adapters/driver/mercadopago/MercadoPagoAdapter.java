package com.br.tc.adapters.driver.mercadopago;

import com.br.tc.core.model.Order;
import com.br.tc.core.ports.service.PaymentPort;
import com.br.tc.infrastructure.http.MercadoPagoClient;

public class MercadoPagoAdapter implements PaymentPort {

    private final MercadoPagoClient mercadoPagoClient;

    public MercadoPagoAdapter(MercadoPagoClient mercadoPagoClient) {
        this.mercadoPagoClient = mercadoPagoClient;
    }

    @Override
    public String gerarQrCode(Order order) {
        return mercadoPagoClient.gerarQrCode(order);
    }
}
