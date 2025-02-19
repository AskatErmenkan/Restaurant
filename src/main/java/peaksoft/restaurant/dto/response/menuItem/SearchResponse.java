package peaksoft.restaurant.dto.response.menuItem;


import lombok.*;
import lombok.experimental.FieldDefaults;
import peaksoft.restaurant.entities.Category;
import peaksoft.restaurant.entities.SubCategory;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchResponse {
        String name;
        Category category;
        SubCategory subCategory;

        public SearchResponse(String name) {
                this.name = name;
        }
}
