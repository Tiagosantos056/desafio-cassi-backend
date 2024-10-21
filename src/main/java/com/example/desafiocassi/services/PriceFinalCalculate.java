package com.example.desafiocassi.services;


import com.example.desafiocassi.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class PriceFinalCalculate {

    public static double CalculatePriceFinal(Product product) {
        double priceFinal = product.getPriceBase();

        switch (product.getCategory()) {
            case "Electronics":
                priceFinal *= 1.10; // Add 10%
                break;

            case "Foods":
                priceFinal *= 1.02; // Add 2%
                break;
            case "Clothing":
                priceFinal *= 1.05; // Add 5%
                break;
            default:
                break;
        }
        return priceFinal;
    }
}
