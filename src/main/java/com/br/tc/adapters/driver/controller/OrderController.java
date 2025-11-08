package com.br.tc.adapters.driver.controller;

import com.br.tc.adapters.driver.mercadopago.PaymentAdapter;
import com.br.tc.adapters.dtos.order.*;
import com.br.tc.adapters.mappers.OrderMapper;
import com.br.tc.core.model.Order;
import com.br.tc.core.model.enums.OrderStatus;
import com.br.tc.core.ports.service.OrderServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order", description = "Operations about order")
@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderServicePort service;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PaymentAdapter paymentAdapter;

    public OrderController(OrderServicePort service, OrderMapper orderMapper, PaymentAdapter paymentAdapter) {
        this.service = service;
        this.orderMapper = orderMapper;
        this.paymentAdapter = paymentAdapter;
    }

    @Operation(summary = "Creates and Checkout an order", description = "Creates and Checkout an order.")
    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class)))
    @PostMapping("/checkout")
    @Transactional
    public ResponseEntity<OrderCheckoutResponseDTO> checkout(@Valid @RequestBody OrderCheckoutRequestDTO orderCheckoutRequestDTO) {

        Order orderToCreate = orderMapper.orderCheckoutRequestDTOToOrder(orderCheckoutRequestDTO);

        Order orderCreated = this.service.createAndCheckout(orderToCreate);

        paymentAdapter.gerarPagamento(new OrderPayment(orderCheckoutRequestDTO.paymentType(), orderCreated));

        return ResponseEntity.ok().body(new OrderCheckoutResponseDTO("Pedido enviado para fila de processamento.", orderMapper.orderToOrderResponseDTO(orderCreated)));
    }

    @Operation(summary = "Returns orders by filter", description = "Return orders by filter.")
    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    @GetMapping
    public ResponseEntity<Page<OrderResponseDTO>> findAllByFilter(@ModelAttribute OrderFilter filter,
                                                                  @PageableDefault(page = 0, size = 10)
                                                                  @SortDefault.SortDefaults({
                                                                          @SortDefault(
                                                                                  sort = "status",
                                                                                  direction = Sort.Direction.DESC),
                                                                          @SortDefault(
                                                                                  sort = "date",
                                                                                  direction = Sort.Direction.ASC)
                                                                  }) Pageable pageable) {
        filter.setStatusNotEquals(OrderStatus.FINISHED);
        Page<Order> page = service.findAllByFilter(filter, pageable);
        List<OrderResponseDTO> list = orderMapper.ordersToOrderResponseDTOs(page.getContent());
        return ResponseEntity.ok().body(new PageImpl<>(list, pageable, page.getTotalElements()));
    }
}
