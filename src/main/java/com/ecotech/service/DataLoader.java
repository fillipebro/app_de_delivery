package com.ecotech.service;

import com.ecotech.model.Product;
import com.ecotech.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private final ProductRepository productRepository;

    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void load() {
        if (productRepository.count() == 0) {
            productRepository.save(Product.builder()
                    .name("Vegano Fast").eco(true).description("100% vegano e entrega por bike.")
                    .image("img/vegano.jpg").price(22.90).build());
            productRepository.save(Product.builder()
                    .name("Pizza Spark").eco(false).description("Pizzas quentinhas")
                    .image("img/pizza.jpg").price(39.90).build());
            productRepository.save(Product.builder()
                    .name("Eco Sushi").eco(true).description("Ingredientes orgânicos e embalagens recicláveis.")
                    .image("img/sushi.jpg").price(49.00).build());
            productRepository.save(Product.builder()
                    .name("Burger Boom com fritas").eco(false).description("Clássicos hambúrgueres com batata.")
                    .image("img/hamburguer.jpg").price(29.50).build());
            productRepository.save(Product.builder()
                    .name("Suco Natural Mix").eco(true).description("Sucos naturais e orgânicos variados.")
                    .image("img/sucos.jpg").price(12.00).build());
            productRepository.save(Product.builder()
                    .name("Refrigerante").eco(false).description("Sua bebida favorita sempre gelada.")
                    .image("img/refri.jpg").price(6.50).build());
        }
    }
}
