package com.br.tc.adapters.dtos.order;

import com.br.tc.core.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        Integer clientId,
        String cpf,
        OrderStatus status,
        BigDecimal totalPrice,
        LocalDateTime date,
        PaymentResponseDTO payment,
        List<OrderItemResponseDTO> items) {
}
