package peaksoft.restaurant.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurant.dto.request.employee.EmployeeRequest;
import peaksoft.restaurant.entities.Employee;
import peaksoft.restaurant.service.EmployeeService;
import peaksoft.restaurant.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeAPI {
    private final RestaurantService restaurantService;
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeRequest saveEmployeeRequest) {
        employeeService.saveEmployee(saveEmployeeRequest);
        return ResponseEntity.ok("Employee saved successfully");
    }

    @GetMapping
    public List<Employee> getEmployees() {
       return employeeService.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PostMapping("/assign/{adminId}/{employeeId}/{restaurantId}")
    public ResponseEntity<String> createEmployeeAssignToRestaurant(@PathVariable Long adminId, @PathVariable Long employeeId, @PathVariable Long restaurantId) {
        employeeService.createEmployeeWithAdmin(adminId, employeeId, restaurantId);
        return ResponseEntity.ok("Employee assigned successfully");
    }
}
