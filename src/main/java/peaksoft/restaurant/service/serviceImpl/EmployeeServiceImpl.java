package peaksoft.restaurant.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Request;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restaurant.dto.request.employee.EmployeeRequest;
import peaksoft.restaurant.dto.response.asimple.SimpleResponse;
import peaksoft.restaurant.dto.response.employee.EmployeeResponse;
import peaksoft.restaurant.entities.Employee;
import peaksoft.restaurant.entities.MenuItem;
import peaksoft.restaurant.entities.Restaurant;
import peaksoft.restaurant.enums.Position;
import peaksoft.restaurant.enums.Role;
import peaksoft.restaurant.repo.EmployeeRepo;
import peaksoft.restaurant.repo.RestaurantRepo;
import peaksoft.restaurant.service.EmployeeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Builder
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final RestaurantRepo restaurantRepo;

    @PostConstruct
    public void initAdmin() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Ala-Too");
        restaurant.setRestType("Bankhet hall");
        restaurant.setLocation("Chyngyz Aitmatov 5");
        restaurant.setNumberOfEmployees(15);
        restaurant.setService(BigDecimal.valueOf(10));
        restaurant.setMenuItem(List.of(
                new MenuItem("Manty",
                        "https://edimdoma.ru/data/recipes/0014/3852/143852-ed4_wide.jpg?1628760277",
                        BigDecimal.valueOf(1500.2), "с мясом", false,
                        null, null, null)));
        restaurant.setEmployees(List.of(
                new Employee("Adi", "Babaev", LocalDate.of(2001,12,21), "adi@gmail.com", "adi123", "+996998114466", Role.WAITER, 5),
                new Employee("Guli", "Nuraeva", LocalDate.of(2004,8,10), "guli@gmail.com", "guli123", "+996999448877", Role.WAITER, 4)));
        if (employeeRepo.findByEmail("admin@peaksoft.com").isEmpty()) {

            Employee admin = new Employee();
            admin.setFirstName("Admin");
            admin.setLastName("Jakaev");
            admin.setDateOfBirth(LocalDate.of(2004,12, 15));
            admin.setEmail("admin@peaksoft.com");
            admin.setPassword("admin1820");
            admin.setExperience(5);
            admin.setPhoneNumber("996702404060");
            admin.setRole(Role.ADMIN);
            admin.setRestaurant(restaurant);
            List<Employee> employees = new ArrayList<>();
            employees.add(admin);
            restaurant.setEmployees(employees);
            employeeRepo.save(admin);
        }
    }

    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest saveEmployeeRequest) {
        Restaurant restaurant = restaurantRepo.findById(saveEmployeeRequest.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Ресторан табылган жок!"));

        if (restaurant.getCurrentNumberOfEmployees() >= 15) {
            throw new IllegalStateException("Ресторанда 15 кызматкерден ашык иштей албайт!");
        }
        if (employeeRepo.existsByEmail(saveEmployeeRequest.getEmail())) {
            throw new RuntimeException("Мындай Email менен катталган!");
        }
        if (saveEmployeeRequest.getRole() == Role.CHEF) {
            LocalDate birthDate = saveEmployeeRequest.getDateOfBirth();
            int age = Period.between(birthDate, LocalDate.now()).getYears();
            if (age < 25 || age > 45) {
                throw new RuntimeException("Ашпозчунун жашы 25тен 45 жашка чейин болуусу керек!");
            }
            if (saveEmployeeRequest.getWorkExperience() < 2) {
                throw new RuntimeException("Ашпозчу үчүн минималдуу иш стажы 2 жыл болушу керек!");
            }
        }
        if (saveEmployeeRequest.getRole() == Role.WAITER) {
            LocalDate birthDate = saveEmployeeRequest.getDateOfBirth();
            int age = Period.between(birthDate, LocalDate.now()).getYears();
            if (age < 18 || age > 30) {
                throw new RuntimeException("Официанттын жашы 18ден 30 жашка чейин болушу керек!");
            }

            if (saveEmployeeRequest.getWorkExperience() < 1) {
                throw new RuntimeException("Официант үчүн минималдуу иш стажы 1 жыл болушу керек!");
            }
        }
        if (saveEmployeeRequest.getPhoneNumber() == null || !saveEmployeeRequest.getPhoneNumber().matches("\\+\\d{12}")) {
            throw new RuntimeException("Телефон номери туура эмес форматта! Формат: +xxxxxxxxxxxx");
        }
        if (saveEmployeeRequest.getPassword().length() < 4) {
            throw new RuntimeException("Пароль 4 символдон кем болбошу керек!");
        }

        Employee employee = new Employee();
        employee.setFirstName(saveEmployeeRequest.getFirstName());
        employee.setLastName(saveEmployeeRequest.getLastName());
        employee.setDateOfBirth(saveEmployeeRequest.getDateOfBirth());

        employee.setEmail(saveEmployeeRequest.getEmail());
        employee.setPassword(saveEmployeeRequest.getPassword()); // Паролду шифрлөө керек!
        employee.setPhoneNumber(saveEmployeeRequest.getPhoneNumber());
        employee.setRole(saveEmployeeRequest.getRole());
        employee.setExperience(saveEmployeeRequest.getExperience());
        employee.setRestaurant(restaurant); //

        employeeRepo.save(employee);

        if (restaurant.getEmployees() == null) {
            restaurant.setEmployees(new ArrayList<>());
        }
        restaurant.getEmployees().add(employee);

        restaurant.setNumberOfEmployees(restaurant.getCurrentNumberOfEmployees() + 1);
        restaurantRepo.save(restaurant);

        return EmployeeResponse.builder()
                .EmployeeId(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .role(employee.getRole())
                .experience(employee.getExperience())
                .build();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public SimpleResponse deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Employee deleted")
                .build();
    }

    @Override
    public EmployeeResponse createEmployeeWithAdmin(Long adminId, Long employeeId, Long restaurantId) {
        Employee admin = employeeRepo.findById(adminId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (admin.getRole() != Role.ADMIN) {
            throw new SecurityException("Сизде уруксат жок!");
        }

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found!"));

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Ресторан табылган жок!"));

        if (restaurant.getCurrentNumberOfEmployees() >= 15) {
            throw new IllegalStateException("Бош орун жок!");
        }

        employee.setRestaurant(restaurant);
        restaurant.getEmployees().add(employee);
        restaurant.setNumberOfEmployees(restaurant.getCurrentNumberOfEmployees() + 1);

        employeeRepo.save(employee);
        restaurantRepo.save(restaurant);

        return new EmployeeResponse(employee.getId(), employee.getName(), restaurant.getName());
    }

////    Сотрудниктер оздору  ресторанга заявка жиберет ,  заявканы админ кабыл алат же албайт.
////    Кабыл алса сотрудник ресторанга assign болот, албаса базадан очуп кетсин.
//    @Override
//    public EmployeeResponse applyForJod(EmployeeRequest employeeRequest) {
//        Employee employee = new Employee();
//        employee.setFirstName(employeeRequest.getFirstName());
//
//        // 🔹 Заявка түзөбүз
//        Request request = new Request();
//        request.setResponse(employee);
//        request.setStatus(RequestStatus.PENDING); // Заявка күтүп турат
//
//        // 🔹 Базага сактоо
//        employeeRepo.save(employee);
//        requestRepo.save(request);
//
//        return new EmployeeResponse(employee.getId(), employee.getName(), "Заявка жөнөтүлдү");
//    }



}
