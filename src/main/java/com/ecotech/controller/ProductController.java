package com.ecotech.controller;

import com.ecotech.model.Product;
import com.ecotech.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repo;
    public ProductController(ProductRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Product> all(@RequestParam(name="eco", required=false) Boolean eco) {
        if (eco != null && eco) {
            return repo.findByEcoTrue();
        }
        return repo.findAll();
    }
}
