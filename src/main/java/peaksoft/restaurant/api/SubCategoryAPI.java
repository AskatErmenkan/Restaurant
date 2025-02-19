package peaksoft.restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurant.dto.request.menuItem.MenuItemRequest;
import peaksoft.restaurant.dto.request.subCategory.SubCategoryRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.category.CategorySortResponse;
import peaksoft.restaurant.dto.response.subCategory.SubCategoryResponse;
import peaksoft.restaurant.entities.MenuItem;
import peaksoft.restaurant.enums.Role;
import peaksoft.restaurant.service.CategoryService;
import peaksoft.restaurant.service.serviceImpl.SubCategoryServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subCategory")
public class SubCategoryAPI {

    private final SubCategoryServiceImpl subCategoryService;

    @PostMapping("/create/{categoryId}")
    public ResponseEntity<String> createSubcategoryWithCategory(
            @PathVariable Long categoryId,
            @RequestBody SubCategoryRequest subCategoryRequest) {
        subCategoryService.createSubCategoryWithCategory(categoryId, subCategoryRequest);
        return ResponseEntity.ok("Субкатегория ийгиликтүү сакталды!");
    }

    @GetMapping("/get/{categoryId}")
    public List<SubCategoryResponse> getSubCategoriesInCategory(@PathVariable Long categoryId) {
        return subCategoryService.getSubCategoriesInCategory(categoryId);
    }

    @GetMapping("/getAll")
    public List<CategorySortResponse> categorySort () {
        return subCategoryService.getSortedSubCategories();
    }
}

