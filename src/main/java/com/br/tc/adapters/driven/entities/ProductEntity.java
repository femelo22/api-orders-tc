//package com.br.tc.adapters.driven.entities;
//
//import com.fastfood.api.core.model.enums.ProductCategory;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "products")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//public class ProductEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @EqualsAndHashCode.Include
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "category", nullable = false)
//    private ProductCategory category;
//
//    @Column(name = "name", nullable = false, length = 155)
//    private String name;
//
//    @Column(name = "price", nullable = false)
//    private BigDecimal price;
//
//    @Column(name = "description", nullable = false, length = 250)
//    private String description;
//
//    @Column(name = "active")
//    private Boolean active;
//
//    @CreationTimestamp
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//}
