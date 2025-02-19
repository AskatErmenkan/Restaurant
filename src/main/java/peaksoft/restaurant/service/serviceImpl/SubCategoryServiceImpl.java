package peaksoft.restaurant.service.serviceImpl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restaurant.dto.request.subCategory.SubCategoryRequest;
import peaksoft.restaurant.dto.response.category.CategorySortResponse;
import peaksoft.restaurant.dto.response.subCategory.SubCategoryResponse;
import peaksoft.restaurant.entities.Category;
import peaksoft.restaurant.entities.SubCategory;
import peaksoft.restaurant.repo.CategoryRepo;
import peaksoft.restaurant.repo.SubCategoryRepo;
import peaksoft.restaurant.service.SubcategoryService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Data
public class SubCategoryServiceImpl implements SubcategoryService {

    private final CategoryRepo categoryRepo;
    private final SubCategoryRepo subcategoryRepo;
    private final SubCategoryRepo subCategoryRepo;


    @Override
    public SubCategoryResponse createSubCategoryWithCategory(Long categoryId, SubCategoryRequest subCategoryRequest) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found!"));

        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryRequest.getName());
        subCategory.setCategory(category);
        subcategoryRepo.save(subCategory);
        return new SubCategoryResponse(subCategory.getId(), subCategory.getName());
    }

    public List<SubCategoryResponse> getSubCategoriesInCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found!"));

        List<SubCategory> subCategories = category.getSubcategories();

        subCategories.sort(Comparator.comparing(SubCategory::getName));

        List<SubCategoryResponse> subCategoryResponses = new ArrayList<>();
        for (SubCategory subCategory : subCategories) {
            subCategoryResponses.add(new SubCategoryResponse(subCategory.getId(), subCategory.getName()));
        }
        return subCategoryResponses;
    }


    @Override
    public List<CategorySortResponse> getSortedSubCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategorySortResponse> finalList = new ArrayList<>();

        for (Category category : categories) {
            List<SubCategoryResponse>subCategoriesResponseList = new ArrayList<>();
            for(SubCategory subcategory:category.getSubcategories()){
                subCategoriesResponseList.add(new SubCategoryResponse(
                        subcategory.getId(),
                        subcategory.getName()));
            }
            finalList.add(new CategorySortResponse(category.getName(), subCategoriesResponseList));
        }
        return finalList;
    }

}

