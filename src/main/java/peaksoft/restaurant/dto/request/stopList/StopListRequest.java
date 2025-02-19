package peaksoft.restaurant.dto.request.stopList;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StopListRequest {
    Long menuItemId;
    String reason;
    LocalDate date;
}
