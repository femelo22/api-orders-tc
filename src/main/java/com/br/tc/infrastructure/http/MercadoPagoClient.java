package com.br.tc.infrastructure.http;

import com.br.tc.core.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-pagamento", path = "${url.api-pagamento}")
public interface MercadoPagoClient {

    @PostMapping("/mercadopago/qrcode")
    String gerarQrCode(@RequestBody Order order);

}
