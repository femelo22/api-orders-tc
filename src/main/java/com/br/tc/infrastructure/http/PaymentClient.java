package com.br.tc.infrastructure.http;

import com.br.tc.core.model.Order;
import com.br.tc.core.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-pagamentos", path = "${url.api-pagamento}")
public interface PaymentClient {

    @PostMapping("/mercadopago/qrcode")
    String gerarQrCode(@RequestBody Order order);

    @PutMapping("/atualizar")
    Payment updatePayment(@RequestBody Payment payment);
}
