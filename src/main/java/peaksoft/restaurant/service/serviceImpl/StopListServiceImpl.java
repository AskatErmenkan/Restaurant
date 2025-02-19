package peaksoft.restaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restaurant.dto.request.stopList.StopListRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.entities.Employee;
import peaksoft.restaurant.entities.MenuItem;
import peaksoft.restaurant.entities.StopList;
import peaksoft.restaurant.enums.Role;
import peaksoft.restaurant.repo.EmployeeRepo;
import peaksoft.restaurant.repo.MenuItemRepo;
import peaksoft.restaurant.repo.StopListRepo;
import peaksoft.restaurant.service.StopListService;

@Service
@Transactional
@RequiredArgsConstructor
public class StopListServiceImpl implements StopListService {
    private final EmployeeRepo employeeRepo;
    private final StopListRepo stopListRepo;
    private final MenuItemRepo menuItemRepo;

    @Override
    public SimpleResponse createStopList(Long employeeId, StopListRequest stopListRequest) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        if (employee.getRole() == Role.WAITER) {
            throw new RuntimeException("Доступ жок!");
        }
        if (stopListRepo.existsByMenuItemIdAndDate(stopListRequest.getMenuItemId(), stopListRequest.getDate())) {
            throw new RuntimeException("Бул тамак ошол датада StopListте бар!");
        }
        MenuItem menuItem = menuItemRepo.findById(stopListRequest.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        StopList stopList = new StopList();
        stopList.setMenuItem(menuItem);
        stopList.setReason(stopListRequest.getReason());
        stopList.setDate(stopListRequest.getDate());
        stopListRepo.save(stopList);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Stop list created")
                .build();
    }
}
