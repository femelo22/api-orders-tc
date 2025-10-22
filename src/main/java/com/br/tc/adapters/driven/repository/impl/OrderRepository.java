package com.br.tc.adapters.driven.repository.impl;

import com.br.tc.adapters.driven.entities.OrderEntity;
import com.br.tc.adapters.driven.repository.SpringOrderRepository;
import com.br.tc.adapters.dtos.order.OrderFilter;
import com.br.tc.adapters.mappers.OrderMapper;
import com.br.tc.core.model.Order;
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
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order create(Order order) {

        OrderEntity orderEntityToSave = orderMapper.orderToOrderEntity(order);
        orderEntityToSave.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntityToSave));
//        orderEntityToSave.getPayment().setOrder(orderEntityToSave);
        orderEntityToSave.setPaymentType(orderEntityToSave.getPaymentType());
        OrderEntity orderEntitySaved = this.springOrderRepository.save(orderEntityToSave);

        return orderMapper.orderEntityToOrder(orderEntitySaved);
    }

    @Override
    public Page<Order> findAllByFilter(OrderFilter filter, Pageable pageable) {

        Page<OrderEntity> ordersEntityPage = this.springOrderRepository.findAll(orderSpecification.getFilter(filter), pageable);
        List<Order> list = orderMapper.orderEntitiesToOrders(ordersEntityPage.getContent());

        return new PageImpl<>(list, pageable, ordersEntityPage.getTotalElements());
    }
}
