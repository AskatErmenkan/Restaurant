package peaksoft.restaurant.service;

import org.springframework.stereotype.Service;
import peaksoft.restaurant.dto.request.employee.EmployeeRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.employee.EmployeeResponse;
import peaksoft.restaurant.entities.Employee;

import java.util.List;

@Service
public interface EmployeeService {

        EmployeeResponse saveEmployee(EmployeeRequest saveEmployeeRequest);

        List<Employee> getAllEmployees();

        SimpleResponse deleteEmployee(Long id);

        EmployeeResponse createEmployeeWithAdmin(Long adminId, Long employeeId, Long restaurantId);

//        Сотрудниктер оздору  ресторанга заявка жиберет ,  заявканы админ кабыл алат же албайт.
//        Кабыл алса сотрудник ресторанга assign болот, албаса базадан очуп кетсин.

//        EmployeeResponse applyForJod(EmployeeRequest employeeRequest);

}
