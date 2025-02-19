package peaksoft.restaurant.service;

import org.springframework.stereotype.Service;
import peaksoft.restaurant.dto.request.restaurant.RestaurantRequest;
import peaksoft.restaurant.dto.response.restaurant.RestaurantResponse;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.entities.Employee;

import java.util.List;

@Service
public interface RestaurantService {

//    TODO Restaurant
    RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest);

    List<RestaurantResponse> getAllRestaurants();

    SimpleResponse deleteRestaurant(Long id);

    SimpleResponse updateRestaurant(Long id, RestaurantRequest restaurantRequest);

}
