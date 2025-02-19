package peaksoft.restaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restaurant.dto.request.menuItem.MenuItemRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.menuItem.MenuItemResponse;
import peaksoft.restaurant.dto.response.menuItem.SearchResponse;
import peaksoft.restaurant.entities.MenuItem;
import peaksoft.restaurant.entities.Restaurant;
import peaksoft.restaurant.enums.Role;
import peaksoft.restaurant.repo.MenuItemRepo;
import peaksoft.restaurant.repo.RestaurantRepo;
import peaksoft.restaurant.service.MenuItemService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
  private final RestaurantRepo restaurantRepo;
  private final MenuItemRepo menuItemRepo;

    @Override
    public SimpleResponse addMenuItem(Long restaurantId, MenuItemRequest menuItemRequest, Role role) {
        if(role != Role.ADMIN && role != Role.CHEF) {
            throw new RuntimeException("Only admin and chef roles are allowed");
        }

        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(()
                -> new RuntimeException("Restaurant not found"));
            MenuItem menuItem = new MenuItem();
            menuItem.setName(menuItemRequest.getName());
            menuItem.setImageUrl(menuItemRequest.getImageUrl());
            menuItem.setPrice(menuItemRequest.getPrice());
            menuItem.setDescription(menuItemRequest.getDescription());
            menuItem.setIsVegetarian(menuItemRequest.getIsVegetarian());
            //------------
            menuItem.setRestaurant(restaurant);
        menuItemRepo.save(menuItem);
        restaurant.getMenuItem().add(menuItem);
        return new SimpleResponse(HttpStatus.OK,"successfully added menu item");
    }

    @Override
    public SimpleResponse createRestaurantMenu(Long restaurantId, MenuItemRequest menuItemRequest) {

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(()->new RuntimeException("Restaurant not found"));
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.getName());
        menuItem.setImageUrl(menuItemRequest.getImageUrl());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setIsVegetarian(menuItemRequest.getIsVegetarian());
        menuItem.setRestaurant(restaurant);
        menuItemRepo.save(menuItem);
        restaurant.getMenuItem().add(menuItem);
        return new SimpleResponse(HttpStatus.OK,"successfully created menu item");
    }

    @Override
    public List<SearchResponse> searchMenuItemByKeyword(String keyword) {
     return   menuItemRepo.searchMenuItemByKeyword(keyword);
    }

    @Override
    public List<MenuItemResponse> sortByPrice(String order) {
       if(order.equals("asc")) {
           return menuItemRepo.findAllByOrderByPriceAsc();
       }else if (order.equals("desc")) {
           return menuItemRepo.findAllByOrderByPriceDesc();
       }
       else {
           throw new RuntimeException("Invalid order");
       }
    }

//    IsVegeterian полеси аркылуу фильтрация болсун
    @Override
    public List<MenuItemResponse> findByIsVegetarian(boolean isVegetarian) {
        return menuItemRepo.findByIsVegetarian(isVegetarian);
    }


}
