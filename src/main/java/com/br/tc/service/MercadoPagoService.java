//package com.br.tc.service;
//
//
//import com.fastfood.api.adapters.dtos.mercadopago.MercadoPagoItemDTO;
//import com.fastfood.api.adapters.dtos.mercadopago.MercadoPagoRequestDTO;
//import com.fastfood.api.adapters.dtos.mercadopago.MercadoPagoResponseDTO;
//import com.fastfood.api.core.model.Order;
//import com.fastfood.api.core.model.OrderItem;
//import com.fastfood.api.core.model.Product;
//import com.fastfood.api.core.ports.service.MercadoPagoServicePort;
//import com.fastfood.api.core.ports.service.ProductServicePort;
//import com.fastfood.api.infrastructure.exceptions.IntegracaoMercadoPagoException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class MercadoPagoService implements MercadoPagoServicePort {
//
//    @Autowired
////    private ProductServicePort productService;
//
//    @Autowired
//    private  RestTemplate restTemplate;
//
//    @Value("${mercadopago.api.url}")
//    private String mercadoPagoUrl;
//
//    @Value("${mercadopago.token}")
//    private String mercadoPagoToken;
//
//    @Value("${mercadopago.notification-url}")
//    private String notificationUrl;
//
//    @Value("${mercadopago.collector-id}")
//    private String collectorId;
//
//    @Value("${mercadopago.pos-id}")
//    private String posId;
//
//    private String unit_measure = "unit";
//
//
//    public String gerarQrCode(Order order) {
//        try {
//            List<MercadoPagoItemDTO> itens = mapOrderItems(order.getItems());
//            MercadoPagoRequestDTO mpRequest = new MercadoPagoRequestDTO();
//            //verificar como faremos, pois ID é criado apenas após persistir no banco
//            mpRequest.setExternal_reference(order.getId().toString());
//            mpRequest.setNotification_url(notificationUrl);
//            mpRequest.setTotal_amount(order.getTotalPrice());
//            mpRequest.setItems(itens);
//            mpRequest.setTitle("Pedido lanchonete");
//            mpRequest.setDescription("Pedido lanchonete");
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setBearerAuth(mercadoPagoToken);
//
//            HttpEntity<MercadoPagoRequestDTO> entity = new HttpEntity<>(mpRequest, headers);
//
//            ResponseEntity<MercadoPagoResponseDTO> response = restTemplate.postForEntity(
//                    mercadoPagoUrl,
//                    entity,
//                    MercadoPagoResponseDTO.class,
//                    collectorId,
//                    posId
//            );
//
//            return response.getBody().getQr_data();
//
//        } catch (Exception e) {
//            throw new IntegracaoMercadoPagoException("Erro ao gerar QR Code com Mercado Pago: " + e.getMessage(), e);
//        }
//    }
//
//    private List<MercadoPagoItemDTO> mapOrderItems(List<OrderItem> orderItems) {
//        return orderItems.stream()
//                .map(orderItem -> {
//                    Product product = productService.findById(orderItem.getProduct().getId());
//                    return new MercadoPagoItemDTO(
//                            product.getId().toString(),
//                            product.getCategory().toString(),
//                            product.getName(),
//                            product.getDescription(),
//                            orderItem.getAmount(),
//                            unit_measure,
//                            product.getPrice(),
//                            product.getPrice().multiply(BigDecimal.valueOf(orderItem.getAmount()))
//                    );
//                })
//                .collect(Collectors.toList());
//    }
//}