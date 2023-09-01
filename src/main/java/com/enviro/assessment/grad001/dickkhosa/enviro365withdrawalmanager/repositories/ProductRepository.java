package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.repositories;

import com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Add custom query methods if needed
}
