package peaksoft.restaurant.dto.response.restaurant;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import peaksoft.restaurant.entities.Employee;
import peaksoft.restaurant.entities.Restaurant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantResponse {
    String name;
    String location;
    String restType;
    int numberOfEmployees;
    BigDecimal service;
    List<Employee> employeeList = new ArrayList<>();

    // RestaurantResponse түзүүчү метод
    public static RestaurantResponse fromRestaurant(Restaurant restaurant) {
        RestaurantResponse response = new RestaurantResponse();
        response.setName(restaurant.getName());
        response.setLocation(restaurant.getLocation());
        response.setRestType(restaurant.getRestType());
        response.setNumberOfEmployees(restaurant.getCurrentNumberOfEmployees());
        response.setService(restaurant.getService());

        response.setEmployeeList(restaurant.getEmployees()); // employees тизмесин тагынуу
        return response;
    }
}
