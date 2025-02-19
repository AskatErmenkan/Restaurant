package peaksoft.restaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.restaurant.dto.response.category.CategoryResponse;
import peaksoft.restaurant.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    boolean existsByName(String name);

    @Query("select new peaksoft.restaurant.dto.response.category.CategoryResponse(c.id, c.name) from Category c")
    List<CategoryResponse> getAllCategories();

//    @Query("select new peaksoft.restaurant.dto.response.category.CategoryResponse(c.id, c.name) from Category c where c.id = :id")
    CategoryResponse getCategoriesById(Long categoryId);


}
