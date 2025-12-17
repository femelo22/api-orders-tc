package com.br.tc.adapters.mappers;

import com.br.tc.adapters.driven.entities.OrderEntity;
import com.br.tc.adapters.driven.entities.OrderItemEntity;
import com.br.tc.adapters.dtos.order.OrderCheckoutRequestDTO;
import com.br.tc.adapters.dtos.order.OrderItemCheckoutRequestDTO;
import com.br.tc.core.model.Order;
import com.br.tc.core.model.OrderItem;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public class OrderConverter {

    public static Order toEntity(OrderCheckoutRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Order order = new Order();

        order.setClientId(dto.clientId());
        order.setCpf(dto.cpf());

        // mesmos comportamentos do mapper
        order.setDate(LocalDateTime.now());
        order.setStatus(null);
        order.setTotalPrice(null);
        order.setItems(convertItems(dto.orderItems()));

        return order;
    }

    private static List<OrderItem> convertItems(List<OrderItemCheckoutRequestDTO> itemsDto) {
        if (itemsDto == null) {
            return List.of();
        }

        return itemsDto.stream()
                .map(OrderItemConverter::toEntity)
                .toList();
    }

    public static OrderEntity toEntity(Order order) {
        if (order == null) {
            return null;
        }

        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setClientId(order.getClientId());
        entity.setCpf(order.getCpf());
        entity.setStatus(order.getStatus());
        entity.setTotalPrice(order.getTotalPrice());
        entity.setDate(order.getDate());

        List<OrderItemEntity> items = convertItemsEntity(order.getItems(), entity);
        entity.setItems(items);

        return entity;
    }

    private static List<OrderItemEntity> convertItemsEntity(
            List<OrderItem> items,
            OrderEntity orderEntity) {

        if (items == null || items.isEmpty()) {
            return List.of();
        }

        return items.stream()
                .map(item -> OrderItemConverter.toEntity(item, orderEntity))
                .toList();
    }

    public static Order toDomain(OrderEntity entity) {
        if (entity == null) {
            return null;
        }

        Order order = new Order();
        order.setId(entity.getId());
        order.setClientId(entity.getClientId());
        order.setCpf(entity.getCpf());
        order.setStatus(entity.getStatus());
        order.setTotalPrice(entity.getTotalPrice());
        order.setDate(entity.getDate());

        List<OrderItem> items = convertItemsDomain(entity.getItems(), order);
        order.setItems(items);

        return order;
    }

    private static List<OrderItem> convertItemsDomain(
            List<OrderItemEntity> entities,
            Order order) {

        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(entity -> OrderItemConverter.toDomain(entity, order))
                .toList();
    }

    public static List<Order> toDomainList(List<OrderEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(OrderConverter::toDomain)
                .toList();
    }

}
