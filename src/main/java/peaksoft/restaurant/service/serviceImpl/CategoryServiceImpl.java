package peaksoft.restaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restaurant.dto.request.category.CategoryRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.category.CategoryResponse;
import peaksoft.restaurant.entities.Category;
import peaksoft.restaurant.repo.CategoryRepo;
import peaksoft.restaurant.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public SimpleResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Category saved")
                .build();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
       return categoryRepo.getAllCategories()!=null ? categoryRepo.getAllCategories():new ArrayList<>();
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()->new RuntimeException("not found"));
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }

    @Override
    public SimpleResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(()->new RuntimeException("not found"));
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);
        return new SimpleResponse(HttpStatus.OK, "Категория жаңыланды!");
    }


    @Override
    public SimpleResponse delete(Long id) {
        Category category = categoryRepo.findById(id).
                orElseThrow(()->new RuntimeException("not found"));
        categoryRepo.delete(category);
        return new SimpleResponse(HttpStatus.OK, "Категория өчүрүлдү");
    }
}
