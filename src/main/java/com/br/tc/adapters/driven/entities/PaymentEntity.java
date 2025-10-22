//package com.br.tc.adapters.driven.entities;
//
//import com.fastfood.api.core.model.enums.PaymentStatus;
//import com.fastfood.api.core.model.enums.PaymentType;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "payments")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//public class PaymentEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @EqualsAndHashCode.Include
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", nullable = false, updatable = false)
//    private OrderEntity order;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "type", nullable = false, length = 50)
//    private PaymentType type;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status", nullable = false, length = 50)
//    private PaymentStatus status;
//
//    @Column(name = "qr_code", nullable = true, length = 200)
//    private String qrCode;
//
//}
