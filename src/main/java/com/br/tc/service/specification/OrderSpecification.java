package com.br.tc.service.specification;

import com.br.tc.adapters.driven.entities.OrderEntity;
import com.br.tc.adapters.dtos.order.OrderFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.data.jpa.domain.Specification.where;


@Component
public class OrderSpecification extends BaseSpecification<OrderEntity, OrderFilter> {

    private static final String ATTR_ID = "id";
    private static final String ATTR_STATUS = "status";
    private static final String ATTR_ORDER_CPF = "cpf";
    private static final String ATTR_TOTAL_PRICE = "totalPrice";
    private static final String ATTR_DATE = "date";

    @Override
    public Specification<OrderEntity> getFilter(OrderFilter filter) {
        return (root, query, cb) ->
                where(attributeEquals(ATTR_ID, filter.getId()))
                        .and(attributeEquals(ATTR_ORDER_CPF, filter.getOrderCpf()))
                        .and(attributeLessThan(ATTR_TOTAL_PRICE, filter.getTotalPriceLessThan()))
                        .and(attributeGreaterThan(ATTR_TOTAL_PRICE, filter.getTotalPriceGreaterThan()))
                        .and(attributeDateBetween(ATTR_DATE, filter.getDateFrom(), filter.getDateTo()))
                        .and(attributeNotEquals(ATTR_STATUS, filter.getStatusNotEquals()))
                        .toPredicate(root, query, cb);
    }

    private Specification<OrderEntity> attributeEquals(String attribute, Object value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.equal(
                    root.get(attribute),
                    value
            );
        };
    }

    private Specification<OrderEntity> attributeNotEquals(String attribute, Object value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.notEqual(
                    root.get(attribute),
                    value
            );
        };
    }

    private Specification<OrderEntity> attributeLessThan(String attribute, BigDecimal value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.lessThan(
                    root.get(attribute),
                    value
            );
        };
    }

    private Specification<OrderEntity> attributeGreaterThan(String attribute, BigDecimal value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            return cb.greaterThan(
                    root.get(attribute),
                    value
            );
        };
    }

    private Specification<OrderEntity> attributeDateBetween(String attribute, LocalDate dtFrom, LocalDate dtTo) {
        return (root, query, cb) -> {
            if (dtFrom == null || dtTo == null) {
                return null;
            }
            return cb.between(cb.function("DATE", LocalDate.class, root.get(attribute)), dtFrom, dtTo);
        };
    }
}
