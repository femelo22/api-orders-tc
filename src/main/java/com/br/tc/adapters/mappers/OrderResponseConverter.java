package com.br.tc.adapters.mappers;

import com.br.tc.adapters.dtos.order.OrderItemResponseDTO;
import com.br.tc.adapters.dtos.order.OrderResponseDTO;
import com.br.tc.core.model.Order;
import com.br.tc.core.model.OrderItem;

import java.util.List;

public class OrderResponseConverter {

    private OrderResponseConverter() {
    }

    public static OrderResponseDTO toDto(Order order) {
        if (order == null) {
            return null;
        }

        return new OrderResponseDTO(
                order.getId(),
                order.getClientId() != null ? order.getClientId().intValue() : null,
                order.getCpf(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getDate(),
                null, // payment ainda não existe na entidade Order
                convertItems(order.getItems())
        );
    }

    private static List<OrderItemResponseDTO> convertItems(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            return List.of();
        }

        return items.stream()
                .map(OrderItemResponseConverter::toDto)
                .toList();
    }

    public static List<OrderResponseDTO> toDtoList(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return List.of();
        }

        return orders.stream()
                .map(OrderResponseConverter::toDto)
                .toList();
    }

}
