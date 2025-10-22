package com.br.tc.adapters.dtos.order;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        Integer productId,
        Integer amount,
        BigDecimal unitPrice,
        String notes) {
}
