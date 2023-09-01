package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.services.interfaces;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Investor;
import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Product;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing products.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
public interface ProductService {

    /**
     * Retrieves the list of products for an investor.
     *
     * @param investor The investor for whom to retrieve the products.
     * @return The list of products.
     */
    List<Product> getProductsForInvestor(Investor investor);

    /**
     * Creates a new product.
     *
     * @param product The product to create.
     * @return The created product.
     */
    Product createProduct(Product product);

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return An optional containing the product if found, or empty if not found.
     */
    Optional<Product> getProductById(Long id);

    /**
     * Retrieves all products.
     *
     * @return The list of all products.
     */
    List<Product> getAllProducts();

    /**
     * Updates a product.
     *
     * @param product The product to update.
     * @return The updated product.
     */
    Product updateProduct(Product product);

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     */
    void deleteProduct(Long id);
}
