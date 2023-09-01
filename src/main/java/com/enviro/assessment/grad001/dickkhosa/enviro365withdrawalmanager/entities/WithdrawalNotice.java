package com.enviro.assessment.grad001.dickkhosa.enviro365withdrawalmanager.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Entity class representing a withdrawal notice.
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
public class WithdrawalNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate noticeDate;

    @NotNull
    private double withdrawalAmount;

    @ManyToOne
    @NotNull
    private Product product;

    /**
     * Constructs a WithdrawalNotice object with the specified attributes.
     *
     * @param noticeDate      The date of the withdrawal notice.
     * @param withdrawalAmount The withdrawal amount.
     * @param product         The associated product.
     */
    public WithdrawalNotice(LocalDate noticeDate, double withdrawalAmount, Product product) {
        this.noticeDate = noticeDate;
        this.withdrawalAmount = withdrawalAmount;
        this.product = product;
    }

    /**
     * Retrieves a list of all withdrawal notices.
     *
     * @return A list of all withdrawal notices.
     */
    public static List<WithdrawalNotice> getAllWithdrawalNotices() {
        // Implement the logic to fetch all withdrawal notices from the database
        // You would typically use a repository or a service to fetch the data
        // For demonstration purposes, I'm returning an empty list here
        return List.of();
    }

    /**
     * Sets the investor for the withdrawal notice.
     *
     * @param investor The investor to set.
     */
    public void setInvestor(Investor investor) {
        // Set the investor for the withdrawal notice
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        WithdrawalNotice that = (WithdrawalNotice) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public Object getAmount() {
        return null;
    }
}
