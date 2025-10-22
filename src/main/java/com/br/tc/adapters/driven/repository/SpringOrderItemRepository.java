package com.br.tc.adapters.driven.repository;

import com.br.tc.adapters.driven.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpringOrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
