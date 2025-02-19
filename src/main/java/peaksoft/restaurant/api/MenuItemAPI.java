package peaksoft.restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurant.dto.request.menuItem.MenuItemRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.menuItem.MenuItemResponse;
import peaksoft.restaurant.dto.response.menuItem.SearchResponse;
import peaksoft.restaurant.enums.Role;
import peaksoft.restaurant.service.MenuItemService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuItemAPI {
    private final MenuItemService menuItemService;


    @PostMapping("/{restaurantId}/{role}")
    public SimpleResponse addMenuItem(@RequestBody MenuItemRequest menuItemRequest,
                                      @PathVariable Long restaurantId, @PathVariable Role role) {
        return menuItemService.addMenuItem(restaurantId, menuItemRequest, role);
    }
    @PostMapping("/createRestaurantMenu/{restaurantId}")
    public SimpleResponse createRestaurantMenu(
            @PathVariable Long restaurantId,
            @RequestBody MenuItemRequest menuItemRequest) {
        return menuItemService.createRestaurantMenu(restaurantId, menuItemRequest);
    }

    @GetMapping("/search/{keyword}")
    public List<SearchResponse> searchMenuItems(@PathVariable String keyword) {
       return  menuItemService.searchMenuItemByKeyword(keyword);
    }
    @GetMapping("/sortByPrice/{order}")
    public List<MenuItemResponse> sortMenuItems(@PathVariable String order) {
        return menuItemService.sortByPrice(order);
    }

    @GetMapping("/findByIsVegetarian/{isVegetarian}")
    public List<MenuItemResponse> searchMenuItemsByIsVegetarian(@PathVariable Boolean isVegetarian) {
        return menuItemService.findByIsVegetarian(isVegetarian);
    }
}
