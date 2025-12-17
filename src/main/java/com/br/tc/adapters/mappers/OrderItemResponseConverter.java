package com.br.tc.adapters.mappers;

import com.br.tc.adapters.dtos.order.OrderItemResponseDTO;
import com.br.tc.core.model.OrderItem;

public class OrderItemResponseConverter {

    public static OrderItemResponseDTO toDto(OrderItem item) {
        if (item == null) {
            return null;
        }

        return new OrderItemResponseDTO(
                item.getId(),
                item.getProductId() != null ? item.getProductId().intValue() : null,
                item.getAmount(),
                item.getUnitPrice(),
                item.getNotes()
        );
    }

}
