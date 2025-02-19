package peaksoft.restaurant.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restaurant.dto.request.cheque.ChequeRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.cheque.ChequeResponse;
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
    private final ChequeService chequeService;
//
//    @Override
//    public SimpleResponse createChequeWithEmployee(Long employeeId, ChequeRequest chequeRequest) {
//        // Эмployeeти табуу
//        Employee employee = employeeRepo.findById(employeeId)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        // Текшерүү: тамактардын тизмеси барбы?
//        List<Long> foodNameIdList = chequeRequest.getFoodNameIdList();
//        if (foodNameIdList == null || foodNameIdList.isEmpty()) {
//            return new SimpleResponse(HttpStatus.BAD_REQUEST, "Foods must be in a non-empty list");
//        }
//
//        // Менюларды табуу
//        List<MenuItem> menuItems = new ArrayList<>();
//        for (Long foodNameId : foodNameIdList) {
//            MenuItem menuItem = menuItemRepo.findById(foodNameId)
//                    .orElseThrow(() -> new RuntimeException("MenuItem with ID " + foodNameId + " not found"));
//            menuItems.add(menuItem);
//        }
//
//        // Чек түзүү
//        Cheque cheque = new Cheque();
//        cheque.setEmployee(employee);
//        employee.getCheques().add(cheque); // Чекти кызматкерге кошуу
//        cheque.setMenuItem(menuItems); // Менюлар тизмесин кошуу
//
//        // Чекти сактоо
//        chequeRepo.save(cheque);
//
//        // Жооп кайтаруу
//        return new SimpleResponse(HttpStatus.OK, "Cheque created successfully");
//    }

//    @Override
//    public ChequeResponseInfo getChequeInfo(Long chequeId) {
//        Cheque cheque = chequeRepo.findById(chequeId)
//                .orElseThrow(() -> new RuntimeException("Cheque not found"));
//
//        Double doubleTotalSum = 0.0;
//        List<MenuItem> menuItems = cheque.getMenuItem();
//
//        for(MenuItem menuItem : menuItems) {
//            doubleTotalSum += menuItem.getPrice().doubleValue();
//        }
//
//        ChequeResponseInfo chequeResponseInfo = new ChequeResponseInfo();
//        chequeResponseInfo.setId(cheque.getId());
//        chequeResponseInfo.setPriceAverage(BigDecimal.valueOf(doubleTotalSum));
//        chequeResponseInfo.setCreatedAt(cheque.getCreatedAt());
//        chequeResponseInfo.setWaiterName(cheque.getEmployee().getFirstName());
//
//        return chequeResponseInfo;
//    }

}
