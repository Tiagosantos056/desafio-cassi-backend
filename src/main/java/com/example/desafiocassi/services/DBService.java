package com.example.desafiocassi.services;

import com.example.desafiocassi.domain.Product;
import com.example.desafiocassi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private ProductRepository productRepository;

    public void startDB() {
        List<Product> products = new ArrayList<>();

        // Adiciona produtos eletrônicos
        for (int i = 1; i <= 10; i++) {
            products.add(new Product((long) i, "Cadeira Ergonômica " + i,
                    "Cadeira ergonômica para jogos " + i,
                    799.99, "Electronics", LocalDate.now()));
        }

        // Adiciona produtos alimentícios
        for (int i = 11; i <= 20; i++) {
            products.add(new Product((long) i, "Marmita Fit " + (i - 10),
                    "Marmita fit para emagrecer " + (i - 10),
                    19.99, "Foods", LocalDate.now()));
        }

        // Adiciona produtos de vestuário
        for (int i = 21; i <= 30; i++) {
            products.add(new Product((long) i, "Vestido de Festa " + (i - 20),
                    "Vestido de festa em geral " + (i - 20),
                    299.99, "Clothing", LocalDate.now()));
        }
        productRepository.saveAll(products);
    }
}
