package peaksoft.restaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.restaurant.dto.response.subCategory.SubCategoryResponse;
import peaksoft.restaurant.entities.SubCategory;

import java.util.List;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {

    List<SubCategoryResponse> findByCategoryIdOrderByName(Long categoryId);


}
