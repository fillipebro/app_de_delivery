package com.ecotech.controller;

import com.ecotech.model.*;
import com.ecotech.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final CartItemRepository cartRepo;
    private final OrderRepository orderRepo;

    public OrderController(CartItemRepository cartRepo, OrderRepository orderRepo) {
        this.cartRepo = cartRepo;
        this.orderRepo = orderRepo;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(HttpServletRequest req) {
        User u = (User) req.getAttribute("authUser"); 
        if (u == null) return ResponseEntity.status(401).body("Not authenticated");

        var cartItems = cartRepo.findByUser(u);
        if (cartItems.isEmpty()) return ResponseEntity.badRequest().body("Carrinho vazio");

        var items = cartItems.stream().map(ci -> OrderItem.builder()
                .productName(ci.getProduct().getName())
                .price(ci.getProduct().getPrice())
                .quantity(ci.getQuantity())
                .build()).collect(Collectors.toList());

        double total = items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();

        OrderEntity order = OrderEntity.builder()
                .user(u)
                .createdAt(Instant.now())
                .items(items)
                .total(total)
                .build();

        orderRepo.save(order);
        cartRepo.deleteByUser(u);
        return ResponseEntity.ok(order);
    }
}
