package com.br.tc.adapters.driver.mercadopago;

import com.br.tc.core.model.Order;
import com.br.tc.core.model.Payment;
import com.br.tc.core.ports.service.PaymentPort;
import com.br.tc.infrastructure.http.PaymentClient;

public class MercadoPagoAdapter implements PaymentPort {

    private final PaymentClient paymentClient;

    public MercadoPagoAdapter(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Override
    public String gerarQrCode(Order order) {
        return paymentClient.gerarQrCode(order);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentClient.updatePayment(payment);
    }


}
