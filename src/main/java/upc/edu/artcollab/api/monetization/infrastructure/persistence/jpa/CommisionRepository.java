/**
 * @summary
 * import Commision class from domain model aggregates
 * and used JpaRepository for Commision and Long
 */

package upc.edu.artcollab.api.monetization.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import upc.edu.artcollab.api.monetization.domain.model.aggregates.Commision;

import java.util.List;
import java.util.Optional;

public interface CommisionRepository extends JpaRepository<Commision, Long> {

    /**
     * @summary
     * declare getAll method for Commision
     * and used Query annotation for select all Commision
     * @return
     * return all Commision
     */
      @Query("SELECT c FROM Commision c")
      List<Commision>getAll();

    /**
     * @summary
     * declare findCommisionById method for Commision
     * and used Query annotation for select Commision by id
     * @param id
     * @return
     * return Commision by id
     */
    Optional<Commision> findCommisionById(long id);

    /**
     * @summary
     * declare findCommisionByAmountGreaterThan method for Commision
     * and used Query annotation for select Commision by amount
     * @param amount
     * @return
     * return Commision by amount
     */
     List<Commision> findCommisionByAmountGreaterThan(double amount);




}
