package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.implementations;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Product;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.repositories.ProductRepository;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing products.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
@Service
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImplementation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves the list of products for an investor.
     *
     * @param investor The investor for whom to retrieve the products.
     * @return The list of products.
     */
    @Override
    public List<Product> getProductsForInvestor(Investor investor) {
        return null;
    }

    /**
     * Creates a new product.
     *
     * @param product The product to create.
     * @return The created product.
     */
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return An optional containing the product if found, or empty if not found.
     */
    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Retrieves all products.
     *
     * @return The list of all products.
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Updates a product's information.
     *
     * @param product The updated product information.
     * @return The updated product.
     */
    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     */
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
