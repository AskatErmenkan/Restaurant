package peaksoft.restaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.restaurant.entities.Cheque;

@Repository
public interface ChequeRepo extends JpaRepository<Cheque, Long> {
}
