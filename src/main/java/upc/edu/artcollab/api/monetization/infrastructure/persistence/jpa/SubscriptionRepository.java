/**
 * @summary
 * This interface is used to interact with the database and perform CRUD operations on the Subscription entity.
 * It extends the JpaRepository interface which provides methods for performing CRUD operations on the entity.
 */

package upc.edu.artcollab.api.monetization.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * @summary
     * declare getAll method for Subscription
     * @return
     * return all Subscription
     */
    @Query("SELECT s FROM Subscription s")
    List<Subscription> getAll();

    /**
     * @summary
     * declare getSubscriptionById method for Subscription
     * @param id
     * @return
     * return Subscription by id
     */
    Optional<Subscription> getSubscriptionById(long id);

    /**
     * @summary
     * declare findSubscriptionByIsActive method for Subscription
     * @param isActive
     * @return
     * return Subscription by isActive
     */
    List<Subscription> findSubscriptionByIsActive(boolean isActive);

}
