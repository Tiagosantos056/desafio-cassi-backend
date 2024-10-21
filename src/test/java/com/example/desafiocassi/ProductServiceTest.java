package com.example.desafiocassi;

import com.example.desafiocassi.domain.Product;
import com.example.desafiocassi.repositories.ProductRepository;
import com.example.desafiocassi.services.Exceptions.ProductNotFoundException;
import com.example.desafiocassi.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setName("Produto Teste");
        product.setPriceBase(100.0);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("Produto Teste", createdProduct.getName());
        assertEquals(100.0, createdProduct.getPriceBase());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testGetProductById_Success() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Produto Teste");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        assertEquals("Produto Teste", foundProduct.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProductById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1L));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Produto Original");
        existingProduct.setPriceBase(50.0);

        Product updatedDetails = new Product();
        updatedDetails.setName("Produto Atualizado");
        updatedDetails.setPriceBase(150.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        Product updatedProduct = productService.updateProduct(1L, updatedDetails);

        assertNotNull(updatedProduct);
        assertEquals("Produto Atualizado", updatedProduct.getName());
        assertEquals(150.0, updatedProduct.getPriceBase());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(product);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    public void testFindAllProductsWithFilters() {
        // Cenário: Criar produtos de exemplo
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Produto A");
        product1.setDescription("Descrição A");
        product1.setCategory("Categoria A");
        product1.setPriceBase(100.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Produto B");
        product2.setDescription("Descrição B");
        product2.setCategory("Categoria B");
        product2.setPriceBase(200.0);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());

        Page<Product> productPage = new PageImpl<>(Arrays.asList(product1, product2), pageable, 2);

        when(productRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(productPage);

        Page<Product> resultAll = productService.getAllProducts(null, null, null, 0, 10, "name");
        assertNotNull(resultAll);
        assertEquals(2, resultAll.getTotalElements());
        assertEquals(2, resultAll.getContent().size());
        assertEquals("Produto A", resultAll.getContent().get(0).getName());
        assertEquals("Produto B", resultAll.getContent().get(1).getName());

        when(productRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(
                new PageImpl<>(Arrays.asList(product1), pageable, 1)
        );
        Page<Product> resultByName = productService.getAllProducts("Produto A", null, null, 0, 10, "name");
        assertNotNull(resultByName);
        assertEquals(1, resultByName.getTotalElements());
        assertEquals("Produto A", resultByName.getContent().get(0).getName());

        when(productRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(
                new PageImpl<>(Arrays.asList(product2), pageable, 1)
        );
        Page<Product> resultByCategory = productService.getAllProducts(null, "Categoria B", null, 0, 10, "name");
        assertNotNull(resultByCategory);
        assertEquals(1, resultByCategory.getTotalElements());
        assertEquals("Produto B", resultByCategory.getContent().get(0).getName());

        when(productRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(
                new PageImpl<>(Arrays.asList(), pageable, 0)
        );
        Page<Product> resultNoMatch = productService.getAllProducts("Produto X", "Categoria Y", "Descrição Z", 0, 10, "name");
        assertNotNull(resultNoMatch);
        assertEquals(0, resultNoMatch.getTotalElements());
        assertTrue(resultNoMatch.getContent().isEmpty());

        verify(productRepository, times(4)).findAll(any(Specification.class), eq(pageable));
    }


}
