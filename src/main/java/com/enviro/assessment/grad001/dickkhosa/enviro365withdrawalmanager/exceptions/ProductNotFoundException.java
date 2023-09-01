package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class for handling product not found errors (HTTP 404).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructs a new ProductNotFoundException with the specified product ID.
     *
     * @param productId The ID of the product that was not found.
     */
    public ProductNotFoundException(Long productId) {
        super("Product with ID " + productId + " not found");
    }
}
