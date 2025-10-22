package com.br.tc.adapters.dtos.order;

import com.br.tc.core.model.enums.PaymentStatus;
import com.br.tc.core.model.enums.PaymentType;

public record PaymentResponseDTO(
        Long id,
        PaymentType type,
        PaymentStatus status,
        String qrCode) {
}
