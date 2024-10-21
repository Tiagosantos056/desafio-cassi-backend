package com.example.desafiocassi.services;

import com.example.desafiocassi.domain.Product;
import com.example.desafiocassi.domain.ProductSpecifications;
import com.example.desafiocassi.repositories.ProductRepository;
import com.example.desafiocassi.services.Exceptions.InvalidProductDataException;
import com.example.desafiocassi.services.Exceptions.ProductNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @SneakyThrows
    @Transactional
    public Product createProduct(Product product) throws Exception {
        validateProductData(product);
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado para o ID: " + id));
    }

    @SneakyThrows
    @Transactional
    public Product updateProduct(Long id, Product productDetails) throws Exception {
        validateProductData(productDetails);
        Product existingProduct = getProductById(id);
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPriceBase(productDetails.getPriceBase());
        existingProduct.setCategory(productDetails.getCategory());
        return productRepository.save(existingProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(String name, String category, String description, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        Specification<Product> specs = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            specs = specs.and(ProductSpecifications.nameContains(name));
        }
        if (category != null && !category.isEmpty()) {
            specs = specs.and(ProductSpecifications.categoryContains(category));
        }
        if (description != null && !description.isEmpty()) {
            specs = specs.and(ProductSpecifications.descriptionContains(description));
        }

        return productRepository.findAll(specs, pageable);
    }

    private void validateProductData(Product product) throws Exception, InvalidProductDataException.InvalidProductDataExceptionOne {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new ProductNotFoundException("O nome do produto não pode ser vazio.");
        }
        if (product.getPriceBase() == null || product.getPriceBase() <= 0) {
            throw new InvalidProductDataException.InvalidProductDataExceptionOne("O preço base do produto deve ser maior que zero.");
        }
    }
}
