package peaksoft.restaurant.repo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.restaurant.entities.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(@NotBlank(message = "Email не может быть пустым") @Email(
            regexp = "^[A-Za-z0-9._%+-]+@(gmail\\.com|yahoo\\.com|[A-Za-z0-9.-]+\\.org)$",
            message = "Email должен быть @gmail.com, @yahoo.com или заканчиваться на .org") String email);

}
