package peaksoft.restaurant.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CategoryRequest {
    @NotBlank(message =  "аты сөзсүз киргизилиши керек!")
    String name;


}
