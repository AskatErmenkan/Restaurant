package peaksoft.restaurant.service;

import org.springframework.stereotype.Service;
import peaksoft.restaurant.dto.request.subCategory.SubCategoryRequest;
import peaksoft.restaurant.dto.response.category.CategorySortResponse;
import peaksoft.restaurant.dto.response.subCategory.SubCategoryResponse;

import java.util.List;

@Service
public interface SubcategoryService {
    SubCategoryResponse createSubCategoryWithCategory(Long categoryId, SubCategoryRequest subCategoryRequest);

    List<SubCategoryResponse> getSubCategoriesInCategory(Long categoryId);

    List<CategorySortResponse> getSortedSubCategories();

}
