package peaksoft.restaurant.dto.request.restaurant;

import jakarta.validation.constraints.Max;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantRequest {
    String name;

    String location;

    String restType;

    @Max(value = 15, message = "Кызматкерлердин саны 15тен ашпашы керек!")
    int numberOfEmployees;

    BigDecimal service;
}

