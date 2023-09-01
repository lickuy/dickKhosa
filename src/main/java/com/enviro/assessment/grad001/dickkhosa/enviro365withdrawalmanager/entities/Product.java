package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Entity class representing a financial product.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ProductType type;
    private String name;
    private double currentBalance;

    // Other fields, constructors, getters, and setters as needed

    /**
     * Enumeration representing the type of the product.
     */
    public enum ProductType {
        RETIREMENT, SAVINGS
    }

    /**
     * Compares this Product to the specified object for equality. The comparison is based on the 'id' field.
     *
     * @param o The object to compare this Product against.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    /**
     * Returns a hash code value for this Product. The hash code is based on the class if this is a proxy object.
     *
     * @return A hash code value for this Product.
     */
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
