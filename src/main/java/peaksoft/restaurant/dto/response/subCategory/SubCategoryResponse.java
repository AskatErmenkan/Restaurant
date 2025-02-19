package peaksoft.restaurant.dto.response.subCategory;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class SubCategoryResponse {
    Long subCategoryId;
    String subCategoryName;

    public SubCategoryResponse(Long id, String name) {
        this.subCategoryId = id;
        this.subCategoryName = name;
    }
}
