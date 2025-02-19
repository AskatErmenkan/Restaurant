package peaksoft.restaurant.repo;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.restaurant.entities.Restaurant;

import java.util.Optional;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

    boolean existsByName(String name);

    Optional<Object> getRestaurantById(@NotNull(message = "ID ресторана не может быть пустым") Long restaurantId);

//    default Restaurant getRestaurantById(Long id) {
//        return findById(id).orElseThrow(() ->
//                new ChangeSetPersister.NotFoundException("Restaurant with " + id + " not found"));
//    }
}
