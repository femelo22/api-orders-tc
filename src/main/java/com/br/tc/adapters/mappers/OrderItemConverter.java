package com.br.tc.adapters.mappers;

import com.br.tc.adapters.driven.entities.OrderEntity;
import com.br.tc.adapters.driven.entities.OrderItemEntity;
import com.br.tc.adapters.dtos.order.OrderItemCheckoutRequestDTO;
import com.br.tc.core.model.Order;
import com.br.tc.core.model.OrderItem;

public class OrderItemConverter {

    public static OrderItem toEntity(OrderItemCheckoutRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        OrderItem item = new OrderItem();
        item.setProductId(dto.productId());
        item.setAmount(dto.amount());
        item.setNotes(dto.notes());

        return item;
    }

    public static OrderItemEntity toEntity(OrderItem item, OrderEntity orderEntity) {
        if (item == null) {
            return null;
        }

        OrderItemEntity entity = new OrderItemEntity();
        entity.setId(item.getId());
        entity.setOrder(orderEntity);
        entity.setProductId(item.getProductId());
        entity.setAmount(item.getAmount());
        entity.setUnitPrice(item.getUnitPrice());
        entity.setNotes(item.getNotes());

        return entity;
    }

    public static OrderItem toDomain(OrderItemEntity entity, Order order) {
        if (entity == null) {
            return null;
        }

        OrderItem item = new OrderItem();
        item.setId(entity.getId());
        item.setOrder(order);
        item.setProductId(entity.getProductId());
        item.setAmount(entity.getAmount());
        item.setUnitPrice(entity.getUnitPrice());
        item.setNotes(entity.getNotes());

        return item;
    }

}
