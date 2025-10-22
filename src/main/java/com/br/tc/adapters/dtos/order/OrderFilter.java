package com.br.tc.adapters.dtos.order;

import com.br.tc.core.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderFilter {

    private Long id;
    private OrderStatus status;
    private String orderCpf;
    private BigDecimal totalPriceLessThan;
    private BigDecimal totalPriceGreaterThan;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer clientId;
    private OrderStatus statusNotEquals;
}

