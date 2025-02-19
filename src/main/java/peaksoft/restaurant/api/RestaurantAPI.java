package peaksoft.restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurant.dto.request.restaurant.RestaurantRequest;
import peaksoft.restaurant.dto.response.restaurant.RestaurantResponse;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantAPI {
    private final RestaurantService restaurantService;

    @PostMapping("/save")
    public ResponseEntity<String> saveRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        restaurantService.saveRestaurant(restaurantRequest);
        return ResponseEntity.ok("Ресторан кошулду!");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RestaurantResponse>> getAll() {
        List<RestaurantResponse> restaurantResponses = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurantResponses);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id); // ресторанды өчүрүү
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Ресторан өчүрүлдү!")
                .build();
    }


    @PutMapping("/update/{id}")
    public SimpleResponse updateRestaurant(@PathVariable("id") Long id, @RequestBody RestaurantRequest restaurantRequest) {
         return restaurantService.updateRestaurant(id, restaurantRequest);
    }
}





//    @PostMapping("/{restaurantId}/employees")
//    public ResponseEntity<String> addEmployee(
//            @PathVariable Long restaurantId,
//            @RequestBody Employee employee) {
//        restaurantService.addEmployee(restaurantId, employee);
//        return ResponseEntity.ok("Кызматкер кошулду!");
//    }
//}
