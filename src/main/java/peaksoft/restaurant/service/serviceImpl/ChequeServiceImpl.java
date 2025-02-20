package peaksoft.restaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import peaksoft.restaurant.dto.request.cheque.ChequeRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.cheque.ChequeResponseInfo;
import peaksoft.restaurant.entities.Cheque;
import peaksoft.restaurant.entities.Employee;
import peaksoft.restaurant.entities.MenuItem;
import peaksoft.restaurant.repo.ChequeRepo;
import peaksoft.restaurant.repo.EmployeeRepo;
import peaksoft.restaurant.repo.MenuItemRepo;
import peaksoft.restaurant.service.ChequeService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepo chequeRepo;
    private final EmployeeRepo employeeRepo;
    private final MenuItemRepo menuItemRepo;

    @Override
    public SimpleResponse createChequeWithEmployee(Long employeeId, ChequeRequest chequeRequest) {
        try {
            Employee employee = employeeRepo.findById(employeeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

            List<Long> foodNameIdList = chequeRequest.getFoodNameIdList();
            if (foodNameIdList == null || foodNameIdList.isEmpty()) {
                return new SimpleResponse(HttpStatus.BAD_REQUEST, "Foods must be in a non-empty list");
            }

            List<MenuItem> menuItems = new ArrayList<>();
            for (Long foodNameId : foodNameIdList) {
                MenuItem menuItem = menuItemRepo.findById(foodNameId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MenuItem with ID " + foodNameId + " not found"));
                menuItems.add(menuItem);
            }

            Cheque cheque = new Cheque();
            cheque.setEmployee(employee);
            employee.getCheques().add(cheque);
            cheque.setMenuItem(menuItems);
            chequeRepo.save(cheque);
            return new SimpleResponse(HttpStatus.OK, "Cheque created successfully");
        } catch (ResponseStatusException ex) {
            return new SimpleResponse((HttpStatus) ex.getStatusCode(), ex.getReason());
        } catch (Exception ex) {
            return new SimpleResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + ex.getMessage());
        }
    }

        @Override
    public ChequeResponseInfo getChequeInfo(Long chequeId) {
        Cheque cheque = chequeRepo.findById(chequeId)
                .orElseThrow(() -> new RuntimeException("Cheque not found"));

        Double doubleTotalSum = 0.0;
        List<MenuItem> menuItems = cheque.getMenuItem();

        for(MenuItem menuItem : menuItems) {
            doubleTotalSum += menuItem.getPrice().doubleValue();
        }
        ChequeResponseInfo chequeResponseInfo = new ChequeResponseInfo();
        chequeResponseInfo.setId(cheque.getId());
        chequeResponseInfo.setPriceAverage(BigDecimal.valueOf(doubleTotalSum));
        chequeResponseInfo.setCreatedAt(cheque.getCreatedAt());
        chequeResponseInfo.setWaiterName(cheque.getEmployee().getFirstName());
        return chequeResponseInfo;
    }
}
