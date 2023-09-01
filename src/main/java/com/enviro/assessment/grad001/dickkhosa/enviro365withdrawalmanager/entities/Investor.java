package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Entity class representing an investor.
 *
 * @author Dick Khosa
 * @since August 28, 2023
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String contact;
    private String email;
    private int age;

    @Getter
    @OneToMany(mappedBy = "investor")
    private List<Product> products;

    @Getter
    private boolean archive;

    /**
     * Constructs an Investor object with the specified attributes.
     *
     * @param name    The name of the investor.
     * @param address The address of the investor.
     * @param contact The contact information of the investor.
     * @param email   The email address of the investor.
     * @param age     The age of the investor.
     */
    public Investor(String name, String address, String contact, String email, int age) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.age = age;
    }

    /**
     * Returns the first name of the investor.
     *
     * @return The first name of the investor.
     */
    public String getFirstName() {
        // Implement logic to extract the first name from the 'name' field
        if (name != null) {
            String[] nameParts = name.split(" ");
            if (nameParts.length > 0) {
                return nameParts[0];
            }
        }
        return "";
    }

    /**
     * Returns the last name of the investor.
     *
     * @return The last name of the investor.
     */
    public String getLastName() {
        // Implement logic to extract the last name from the 'name' field
        if (name != null) {
            String[] nameParts = name.split(" ");
            if (nameParts.length > 1) {
                return nameParts[nameParts.length - 1];
            }
        }
        return "";
    }

    /**
     * Sets the first name of the investor.
     *
     * @param firstName The new first name.
     */
    public void setFirstName(String firstName) {
        // Implement logic to update the first name in the 'name' field
        if (name != null) {
            String[] nameParts = name.split(" ");
            if (nameParts.length > 1) {
                nameParts[0] = firstName;
                name = String.join(" ", nameParts);
            }
        }
    }

    /**
     * Sets the last name of the investor.
     *
     * @param lastName The new last name.
     */
    public void setLastName(String lastName) {
        // Implement logic to update the last name in the 'name' field
        if (name != null) {
            String[] nameParts = name.split(" ");
            if (nameParts.length > 1) {
                nameParts[nameParts.length - 1] = lastName;
                name = String.join(" ", nameParts);
            }
        }
    }

    /**
     * Sets the archive status of the investor.
     *
     * @param archive True if the investor is archived, false otherwise.
     */
    public void setArchived(boolean archive) {
        this.archive = archive;
    }


        // Other fields and methods

        @Getter
        private LocalDate dateOfBirth;

    public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        // Other getters and setters
    }


