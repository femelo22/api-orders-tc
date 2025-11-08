package com.br.tc.adapters.dtos.order;

import com.br.tc.core.model.Order;
import com.br.tc.core.model.enums.PaymentType;

public record OrderPayment(PaymentType paymentType, Order order) {
}
