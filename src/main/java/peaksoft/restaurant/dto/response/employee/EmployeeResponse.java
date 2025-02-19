package peaksoft.restaurant.dto.response.employee;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import peaksoft.restaurant.enums.Role;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {
    Long EmployeeId;
    Long RestaurantId;
    String firstName;
    String lastName;
//    LocalDate dateOfBirth;
//    String email;
//    String password;
//    String phoneNumber;
    @Enumerated(EnumType.STRING)
    Role role;
    int experience;
    HttpStatus httpStatus;
    String message;

    public EmployeeResponse(Long id, Object name, String name1) {
        EmployeeId = id;
    }
}
