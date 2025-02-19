package peaksoft.restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurant.dto.request.category.CategoryRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.category.CategoryResponse;
import peaksoft.restaurant.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryAPI {
    private final CategoryService categoryService;

    @PostMapping
    public SimpleResponse saveCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.saveCategory(categoryRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Категория сакталды!")
                .build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategory() {
        List<CategoryResponse> categoryResponses = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse updateCategory(@PathVariable("id") Long id, @RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategory(id, categoryRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteCategory(@PathVariable("id") Long id) {
        return categoryService.delete(id);
    }
}
