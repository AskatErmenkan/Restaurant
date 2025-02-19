package peaksoft.restaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.restaurant.entities.StopList;

import java.time.LocalDate;

@Repository
public interface StopListRepo extends JpaRepository<StopList, Long> {
    boolean existsByMenuItemIdAndDate(Long menuItemId, LocalDate date);

}

