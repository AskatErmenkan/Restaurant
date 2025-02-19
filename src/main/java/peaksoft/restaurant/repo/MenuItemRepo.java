package peaksoft.restaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.restaurant.dto.response.menuItem.MenuItemResponse;
import peaksoft.restaurant.dto.response.menuItem.SearchResponse;
import peaksoft.restaurant.entities.MenuItem;

import java.util.List;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {

    @Query("SELECT new peaksoft.restaurant.dto.response.menuItem.SearchResponse " +
            "(m.name) FROM MenuItem m " +
            "WHERE LOWER(m.name) LIKE LOWER(CONCAT(:keyword, '%'))")
    List<SearchResponse> searchMenuItemByKeyword(@Param("keyword") String keyword);

    @Query("select new peaksoft.restaurant.dto.response.menuItem.MenuItemResponse " +
            "(m.id,m.name,m.imageUrl,m.price,m.description, m.isVegetarian) from MenuItem m order by m.price asc ")
    List<MenuItemResponse> findAllByOrderByPriceAsc();
    @Query("select new peaksoft.restaurant.dto.response.menuItem.MenuItemResponse " +
            "(m.id,m.name,m.imageUrl,m.price,m.description, m.isVegetarian) from MenuItem m order by m.price desc")
    List<MenuItemResponse> findAllByOrderByPriceDesc();

    @Query("select new peaksoft.restaurant.dto.response.menuItem.MenuItemResponse " +
            "(m.id,m.name,m.imageUrl,m.price,m.description, m.isVegetarian) from MenuItem m where m.isVegetarian =:isVegetarian")
    List<MenuItemResponse> findByIsVegetarian(@Param("isVegetarian") boolean isVegetarian);
}
