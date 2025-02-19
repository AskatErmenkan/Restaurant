package peaksoft.restaurant.service.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.restaurant.dto.request.restaurant.RestaurantRequest;
import peaksoft.restaurant.dto.response.restaurant.RestaurantResponse;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.entities.Employee;
import peaksoft.restaurant.entities.Restaurant;
import peaksoft.restaurant.repo.RestaurantRepo;
import peaksoft.restaurant.service.RestaurantService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo restaurantRepo;


    @Override
    public RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest) {
//        if (restaurantRepo.existsByName(restaurantRequest.getName())) {
//            throw new BadRequestException("Restaurant name already exists");
//        }

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setNumberOfEmployees(restaurantRequest.getNumberOfEmployees());
        restaurant.setService(restaurantRequest.getService());
        restaurantRepo.save(restaurant);

        // RestaurantResponse объектисин түзүү жана толтуруу
        RestaurantResponse restaurantResponse = new RestaurantResponse();
        restaurantResponse.setName(restaurant.getName());
        restaurantResponse.setLocation(restaurant.getLocation());
        restaurantResponse.setRestType(restaurant.getRestType());
        restaurantResponse.setNumberOfEmployees(restaurant.getNumberOfEmployees());
        restaurantResponse.setService(restaurant.getService());

        // RestaurantResponse объектисин кайтаруу
        return restaurantResponse;
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants.stream()
                .map(RestaurantResponse::fromRestaurant)
                .collect(Collectors.toList());
    }

    @Override
    public SimpleResponse deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Restaurant deleted").build();
    }

    @Override
    public SimpleResponse updateRestaurant(Long id, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(null);
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setNumberOfEmployees(restaurantRequest.getNumberOfEmployees());
        restaurant.setService(restaurantRequest.getService());
        restaurantRepo.save(restaurant);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Restaurant updated")
                .build();
    }
}
