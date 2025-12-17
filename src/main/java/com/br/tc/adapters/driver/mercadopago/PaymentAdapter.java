package com.br.tc.adapters.driver.mercadopago;

import com.br.tc.adapters.dtos.order.OrderPayment;
import com.br.tc.core.model.Payment;
import com.br.tc.core.ports.service.PaymentPort;
import com.br.tc.infrastructure.http.PaymentClient;
import org.springframework.stereotype.Service;

@Service
public class PaymentAdapter implements PaymentPort {

    private final PaymentClient paymentClient;

    public PaymentAdapter(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentClient.updatePayment(payment);
    }

    @Override
    public void gerarPagamento(OrderPayment newPayment) {
        paymentClient.enviarPagamento(newPayment);
    }


}
