package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.controllers.rest;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Product;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductControllerTest {

    @Test
    public void test_createProduct_validInput() {
        // Create a mock ProductService
        ProductService productService = Mockito.mock(ProductService.class);

        // Create a ProductController instance with the mock ProductService
        ProductController productController = new ProductController(productService);

        // Create a new Product object with valid input
        Product product = new Product();
        product.setName("Test Product");
        product.setType(Product.ProductType.RETIREMENT);
        product.setCurrentBalance(1000.0);

        // Create a ResponseEntity with the created product and HTTP status CREATED
        ResponseEntity<Product> expectedResponse = ResponseEntity.status(HttpStatus.CREATED).body(product);

        // Mock the createProduct method of the ProductService to return the expected response
        Mockito.when(productService.createProduct(Mockito.any(Product.class))).thenReturn(product);

        // Call the createProduct method of the ProductController
        ResponseEntity<Product> actualResponse = productController.createProduct(product);

        // Assert that the actual response is equal to the expected response
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void test_getProductById_existingId() {
        // Create a mock ProductService
        ProductService productService = Mockito.mock(ProductService.class);

        // Create a ProductController instance with the mock ProductService
        ProductController productController = new ProductController(productService);

        // Create a product with an existing ID
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setType(Product.ProductType.RETIREMENT);
        product.setCurrentBalance(1000.0);

        // Create an Optional containing the product
        Optional<Product> expectedProduct = Optional.of(product);

        // Create a ResponseEntity with the retrieved product and HTTP status OK
        ResponseEntity<Product> expectedResponse = ResponseEntity.ok(product);

        // Mock the getProductById method of the ProductService to return the expected product
        Mockito.when(productService.getProductById(productId)).thenReturn(expectedProduct);

        // Call the getProductById method of the ProductController
        ResponseEntity<Product> actualResponse = productController.getProductById(productId);

        // Assert that the actual response is equal to the expected response
        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    public void test_getProductById_nonExistingId() {
        // Create a mock ProductService
        ProductService productService = Mockito.mock(ProductService.class);

        // Create a ProductController instance with the mock ProductService
        ProductController productController = new ProductController(productService);

        // Create a product with a non-existing ID
        Long productId = 1L;

        // Create an Optional containing an empty value
        Optional<Product> expectedProduct = Optional.empty();

        // Create a ResponseEntity with HTTP status NOT FOUND
        ResponseEntity<Product> expectedResponse = ResponseEntity.notFound().build();

        // Mock the getProductById method of the ProductService to return the expected product
        Mockito.when(productService.getProductById(productId)).thenReturn(expectedProduct);

        // Call the getProductById method of the ProductController
        ResponseEntity<Product> actualResponse = productController.getProductById(productId);

        // Assert that the actual response is equal to the expected response
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void test_getAllProducts() {
        // Create a mock ProductService
        ProductService productService = Mockito.mock(ProductService.class);

        // Create a ProductController instance with the mock ProductService
        ProductController productController = new ProductController(productService);

        // Create a list of products
        List<Product> expectedProducts = new ArrayList<>();

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setType(Product.ProductType.RETIREMENT);
        product1.setCurrentBalance(1000.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setType(Product.ProductType.SAVINGS);
        product2.setCurrentBalance(2000.0);

        expectedProducts.add(product1);
        expectedProducts.add(product2);

        // Mock the getAllProducts method of the ProductService to return the expected list of products
        Mockito.when(productService.getAllProducts()).thenReturn(expectedProducts);

        // Call the getAllProducts method of the ProductController
        List<Product> actualProducts = productController.getAllProducts();

        // Assert that the actual list of products is equal to the expected list of products
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void test_updateProduct_existingId() {
        // Create a mock ProductService
        ProductService productService = Mockito.mock(ProductService.class);

        // Create a ProductController instance with the mock ProductService
        ProductController productController = new ProductController(productService);

        // Create an existing product with valid input
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Existing Product");
        existingProduct.setType(Product.ProductType.RETIREMENT);
        existingProduct.setCurrentBalance(1000.0);

        // Create an updated product with valid input
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setType(Product.ProductType.SAVINGS);
        updatedProduct.setCurrentBalance(2000.0);

        // Create an Optional containing the existing product
        Optional<Product> expectedExistingProduct = Optional.of(existingProduct);

        // Create a ResponseEntity with the updated product and HTTP status OK
        ResponseEntity<Product> expectedResponse = ResponseEntity.ok(updatedProduct);

        // Mock the getProductById method of the ProductService to return the expected existing product
        Mockito.when(productService.getProductById(productId)).thenReturn(expectedExistingProduct);

        // Mock the updateProduct method of the ProductService to return the updated product
        Mockito.when(productService.updateProduct(Mockito.any(Product.class))).thenReturn(updatedProduct);

        // Call the updateProduct method of the ProductController
        ResponseEntity<Product> actualResponse = productController.updateProduct(productId, updatedProduct);

        // Assert that the actual response is equal to the expected response
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void test_updateProduct_nonExistingId() {
        // Create a mock ProductService
        ProductService productService = Mockito.mock(ProductService.class);

        // Create a ProductController instance with the mock ProductService
        ProductController productController = new ProductController(productService);

        // Create a product with a non-existing ID
        Long productId = 1L;
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setType(Product.ProductType.SAVINGS);
        updatedProduct.setCurrentBalance(2000.0);

        // Create an Optional containing an empty value
        Optional<Product> expectedExistingProduct = Optional.empty();

        // Create a ResponseEntity with HTTP status NOT FOUND
        ResponseEntity<Product> expectedResponse = ResponseEntity.notFound().build();

        // Mock the getProductById method of the ProductService to return the expected existing product
        Mockito.when(productService.getProductById(productId)).thenReturn(expectedExistingProduct);

        // Call the updateProduct method of the ProductController
        ResponseEntity<Product> actualResponse = productController.updateProduct(productId, updatedProduct);

        // Assert that the actual response is equal to the expected response
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void test_deleteProduct_existingId() {
        // Create a mock ProductService
        ProductService productService = Mockito.mock(ProductService.class);

        // Create a ProductController instance with the mock ProductService
        ProductController productController = new ProductController(productService);

        // Create an existing product
        Long productId = 1L;

        // Create an Optional containing an existing product
        Optional<Product> expectedExistingProduct = Optional.of(new Product());

        // Mock the getProductById method of the ProductService to return the expected existing product
        Mockito.when(productService.getProductById(productId)).thenReturn(expectedExistingProduct);

        // Call the deleteProduct method of the ProductController
        ResponseEntity<Void> actualResponse = productController.deleteProduct(productId);

        // Assert that the actual response has HTTP status NO_CONTENT
        assertEquals(HttpStatus.NO_CONTENT, actualResponse.getStatusCode());
    }
}
