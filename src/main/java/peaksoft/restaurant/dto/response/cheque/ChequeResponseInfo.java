package peaksoft.restaurant.dto.response.cheque;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ChequeResponseInfo {
    Long id;
    String waiterName;
    Double service;
    BigDecimal priceAverage;
    LocalDateTime createdAt;
    List<String> menuItems;
}

