package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.implementations;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Product;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceImplementationTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImplementation productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        Product productToCreate = new Product();
        when(productRepository.save(productToCreate)).thenReturn(productToCreate);

        Product createdProduct = productService.createProduct(productToCreate);

        assertNotNull(createdProduct);
        verify(productRepository, times(1)).save(productToCreate);
    }

    @Test
    public void testGetProductByIdSuccess() {
        Long productId = 1L;
        Product mockProduct = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        Optional<Product> retrievedProduct = productService.getProductById(productId);

        assertTrue(retrievedProduct.isPresent());
        assertEquals(mockProduct, retrievedProduct.get());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testGetProductByIdNotFound() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Optional<Product> retrievedProduct = productService.getProductById(productId);

        assertTrue(retrievedProduct.isEmpty());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product());
        mockProducts.add(new Product());
        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> allProducts = productService.getAllProducts();

        assertNotNull(allProducts);
        assertEquals(mockProducts.size(), allProducts.size());
        verify(productRepository, times(1)).findAll();
    }


    @Test
    public void testDeleteProduct() {
        Long productId = 1L;

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }


}
