package peaksoft.restaurant.service;

import org.springframework.stereotype.Service;
import peaksoft.restaurant.dto.request.menuItem.MenuItemRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.menuItem.MenuItemResponse;
import peaksoft.restaurant.dto.response.menuItem.SearchResponse;
import peaksoft.restaurant.enums.Role;

import java.util.List;

@Service
public interface MenuItemService {
    SimpleResponse addMenuItem(Long restaurantId, MenuItemRequest menuItemRequest, Role role);

    SimpleResponse createRestaurantMenu(Long restaurantId, MenuItemRequest menuItemRequest);

    List<SearchResponse> searchMenuItemByKeyword(String keyword);

    List<MenuItemResponse> sortByPrice(String order);

    List<MenuItemResponse> findByIsVegetarian(boolean isVegetarian);


}
