package peaksoft.restaurant.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "restaurants")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_gen")
    @SequenceGenerator(name = "restaurant_gen", sequenceName = "restaurant_seq", allocationSize = 1)
    Long id;
    String name;
    String location;
    String restType;
    @Max(value = 15)
    int numberOfEmployees = 0;
    BigDecimal service;

    @OneToMany(mappedBy = "restaurant")
    List<Employee> employees;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<MenuItem> menuItem;

    // Учурдагы кызматкерлердин санын кайтарып берүү
    public int getCurrentNumberOfEmployees() {
        return employees != null ? employees.size() : 0;
    }
    // Ресторанга кызматкер кошо алса болорун текшерүү (максимум 15)
    public boolean canHireMoreEmployees() {
        return getCurrentNumberOfEmployees() < 15;
    }
}
