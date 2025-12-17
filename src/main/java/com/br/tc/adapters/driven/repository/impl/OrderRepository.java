package com.br.tc.adapters.driven.repository.impl;

import com.br.tc.adapters.driven.entities.OrderEntity;
import com.br.tc.adapters.driven.repository.SpringOrderRepository;
import com.br.tc.adapters.dtos.order.OrderFilter;
import com.br.tc.adapters.mappers.OrderConverter;
//import com.br.tc.adapters.mappers.OrderMapper;
import com.br.tc.core.model.Order;
import com.br.tc.core.model.OrderItem;
import com.br.tc.core.ports.repository.OrderRepositoryPort;
import com.br.tc.service.specification.OrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OrderRepository implements OrderRepositoryPort {

    @Autowired
    private OrderSpecification orderSpecification;
    @Autowired
    private SpringOrderRepository springOrderRepository;


    @Override
    public Order create(Order order) {

        OrderEntity orderEntityToSave = OrderConverter.toEntity(order);
        orderEntityToSave.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntityToSave));
        OrderEntity orderEntitySaved = this.springOrderRepository.save(orderEntityToSave);

        return OrderConverter.toDomain(orderEntitySaved);
    }

    @Override
    public Page<Order> findAllByFilter(OrderFilter filter, Pageable pageable) {

        Page<OrderEntity> ordersEntityPage = this.springOrderRepository.findAll(orderSpecification.getFilter(filter), pageable);


        List<Order> list = OrderConverter.toDomainList(ordersEntityPage.getContent());

        return new PageImpl<>(list, pageable, ordersEntityPage.getTotalElements());
    }
}
