package com.ecotech.controller;

import com.ecotech.model.CartItem;
import com.ecotech.model.User;
import com.ecotech.repository.CartItemRepository;
import com.ecotech.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public CartController(CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/add/{email}")
    public ResponseEntity<?> addItem(@PathVariable String email, @RequestBody CartItem item) {
        return userRepository.findByEmail(email).map(user -> {
            item.setUser(user);
            return ResponseEntity.ok(cartItemRepository.save(item));
        }).orElse(ResponseEntity.badRequest().body("Usuário não encontrado"));
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getCart(@PathVariable String email) {
        return userRepository.findByEmail(email).map(user -> {
            List<CartItem> items = cartItemRepository.findByUser(user);
            return ResponseEntity.ok(items);
        }).orElse(ResponseEntity.badRequest().body("Usuário não encontrado"));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> clearCart(@PathVariable String email) {
        return userRepository.findByEmail(email).map(user -> {
            List<CartItem> items = cartItemRepository.findByUser(user);
            cartItemRepository.deleteAll(items);
            return ResponseEntity.ok("Carrinho limpo com sucesso!");
        }).orElse(ResponseEntity.badRequest().body("Usuário não encontrado"));
    }
}
