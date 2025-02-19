package peaksoft.restaurant.dto.request.menuItem;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemRequest {
    String name;
    String imageUrl;
    @PositiveOrZero(message = "Баасы терс сан болбошу керек!")
    BigDecimal price;
    String description;
    Boolean isVegetarian;


}
