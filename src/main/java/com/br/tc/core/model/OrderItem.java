package com.br.tc.core.model;

import java.math.BigDecimal;

public class OrderItem {

    private Long id;
    private Order order;
    private Long productId;
    private Integer amount;
    private BigDecimal unitPrice;
    private String notes;

    public OrderItem(Long id, Order order, Long productId, Integer amount, BigDecimal unitPrice, String notes) {
        this.id = id;
        this.order = order;
        this.productId = productId;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.notes = notes;
    }

    public OrderItem() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
