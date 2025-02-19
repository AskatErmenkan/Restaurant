package peaksoft.restaurant.service;

import org.springframework.stereotype.Service;
import peaksoft.restaurant.dto.request.category.CategoryRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.category.CategoryResponse;

import java.util.List;

@Service
public interface CategoryService {
    SimpleResponse saveCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategoryById(Long categoryId);

    SimpleResponse updateCategory(Long id, CategoryRequest categoryRequest);

    SimpleResponse delete(Long id);

//    GetCategoryResponse getCategory(Long id);
}
