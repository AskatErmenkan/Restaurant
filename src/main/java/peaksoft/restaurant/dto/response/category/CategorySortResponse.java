package peaksoft.restaurant.dto.response.category;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.restaurant.dto.response.subCategory.SubCategoryResponse;

import java.util.List;



public class CategorySortResponse {
    private String categoryName;
    private List<SubCategoryResponse> subCategories;

    public CategorySortResponse(String categoryName, List<SubCategoryResponse> subCategories) {
        this.categoryName = categoryName;
        this.subCategories = subCategories;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<SubCategoryResponse> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryResponse> subCategories) {
        this.subCategories = subCategories;
    }
}
