package peaksoft.restaurant.dto.response.menuItem;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemResponse {
    Long id;
    String name;
    String imageUrl;
    BigDecimal price;
    String description;
    Boolean isVegetarian;


}
