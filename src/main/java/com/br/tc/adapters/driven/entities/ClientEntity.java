//package com.br.tc.adapters.driven.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "clients")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//public class ClientEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @EqualsAndHashCode.Include
//    private Long id;
//
//    @Column(name = "cpf", unique = true, length = 11)
//    private String cpf;
//
//    @Column(name = "name", nullable = false, length = 155)
//    private String name;
//
//    @Column(name = "email", nullable = false, unique = true, length = 155)
//    private String email;
//
//    @Column(name = "phone", length = 55)
//    private String phone;
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
